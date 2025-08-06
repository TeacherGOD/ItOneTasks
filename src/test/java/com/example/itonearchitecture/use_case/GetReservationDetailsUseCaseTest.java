package com.example.itonearchitecture.use_case;

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
        Long reservationId = 1L;
        Reservation reservation = new Reservation(2L, LocalDateTime.now(), 4);
        reservation.setId(reservationId);

        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        ReservationResponse response = useCase.execute(reservationId);

        assertNotNull(response);
        assertEquals(reservationId, response.id());
        assertEquals(2L, response.tableId());
    }

    @Test
    void execute_WhenReservationNotFound_ShouldThrowException() {
        Long reservationId = 99L;
        when(repository.findById(reservationId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                useCase.execute(reservationId));
    }
}
