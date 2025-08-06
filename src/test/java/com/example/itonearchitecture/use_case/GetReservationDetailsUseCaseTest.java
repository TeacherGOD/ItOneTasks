package com.example.itonearchitecture.use_case;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.ReservationRepository;
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
class GetReservationDetailsUseCaseTest {

    @Mock
    private ReservationRepository repository;

    @InjectMocks
    private GetReservationDetailsUseCase useCase;

    @Test
    void execute_WhenReservationExists_ShouldReturnResponse() {
        ReservationId reservationId = new ReservationId(1L);
        TableId tableId = new TableId(2L);
        ReservationTime time = new ReservationTime(LocalDateTime.now());
        Reservation reservation = new Reservation(tableId, time, 4);
        reservation.assignId(reservationId);

        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        ReservationResponse response = useCase.execute(reservationId.value());

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(2L, response.tableId());
    }
}
