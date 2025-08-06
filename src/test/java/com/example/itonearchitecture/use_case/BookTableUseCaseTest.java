package com.example.itonearchitecture.use_case;

import com.example.itonearchitecture.dto.BookTableRequest;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookTableUseCaseTest {

    @Mock
    private ReservationRepository repository;

    @InjectMocks
    private BookTableUseCase bookTableUseCase;

    @Test
    void execute_WhenTableAvailable_ShouldReturnReservationResponse() {
        BookTableRequest request = new BookTableRequest(1L, LocalDateTime.now(), 4);

        when(repository.existsByTableAndTime(any(), any())).thenReturn(false);
        when(repository.save(any())).thenAnswer(inv -> {
            Reservation r = inv.getArgument(0);
            r.setId(1L);
            return r;
        });

        ReservationResponse response = bookTableUseCase.execute(request);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(1L, response.tableId());
        verify(repository).save(any(Reservation.class));
    }

    @Test
    void execute_WhenTableBooked_ShouldThrowException() {
        BookTableRequest request = new BookTableRequest(1L, LocalDateTime.now(), 4);
        when(repository.existsByTableAndTime(any(), any())).thenReturn(true);

        assertThrows(IllegalStateException.class, () ->
                bookTableUseCase.execute(request));
        verify(repository, never()).save(any());
    }
}