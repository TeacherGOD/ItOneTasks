package com.example.itonearchitecture.use_case;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.service.TableAssignmentService;
import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.dto.BookTableRequest;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.ReservationRepository;
import com.example.itonearchitecture.repository.TableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookTableUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private TableAssignmentService tableAssignmentService;

    @InjectMocks
    private BookTableUseCase bookTableUseCase;

    @Test
    void execute_WhenTableAvailable_ShouldReturnReservationResponse() {
        BookTableRequest request = new BookTableRequest(LocalDateTime.now().plusHours(1), 4);
        TableId tableId = new TableId(1L);
        Reservation reservation = new Reservation(tableId, new ReservationTime(request.dateTime()), request.guests());
        reservation.assignId(new ReservationId(1L));

        when(tableAssignmentService.findAvailableTable(anyInt(), any()))
                .thenReturn(Optional.of(tableId));
        when(reservationRepository.save(any())).thenReturn(reservation);

        ReservationResponse response = bookTableUseCase.execute(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(1L, response.tableId());
        verify(reservationRepository).save(any(Reservation.class));
    }

    @Test
    void execute_WhenAllTablesBooked_ShouldThrowException() {
        BookTableRequest request = new BookTableRequest(LocalDateTime.now().plusHours(1), 4);

        when(tableAssignmentService.findAvailableTable(anyInt(), any(ReservationTime.class)))
                .thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () ->
                bookTableUseCase.execute(request));

        verify(reservationRepository, never()).save(any());
    }
}