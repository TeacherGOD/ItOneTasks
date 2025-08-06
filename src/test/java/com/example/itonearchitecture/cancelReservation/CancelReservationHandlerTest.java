package com.example.itonearchitecture.cancelReservation;

import com.example.itonearchitecture.cancel_reservation.CancelReservationCommand;
import com.example.itonearchitecture.cancel_reservation.CancelReservationHandler;
import com.example.itonearchitecture.cancel_reservation.CancelReservationRepository;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.entity.ReservationStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CancelReservationHandlerTest {

    @Mock
    private CancelReservationRepository repository;

    @InjectMocks
    private CancelReservationHandler handler;

    @Test
    void handle_ShouldCancelReservation() {
        // Arrange
        Long reservationId = 1L;
        Reservation reservation = new Reservation(2L, LocalDateTime.now(), 4);
        reservation.setId(reservationId);

        CancelReservationCommand command = new CancelReservationCommand(reservationId);

        when(repository.findById(reservationId)).thenReturn(reservation);

        handler.handle(command);

        verify(repository).save(reservation);
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void handle_WhenReservationNotFound_ShouldThrowException() {
        Long reservationId = 99L;
        CancelReservationCommand command = new CancelReservationCommand(reservationId);

        when(repository.findById(reservationId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> handler.handle(command));
        verify(repository, never()).save(any());
    }
}