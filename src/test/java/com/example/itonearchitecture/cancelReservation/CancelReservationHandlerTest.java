package com.example.itonearchitecture.cancelReservation;

import com.example.itonearchitecture.cancel_reservation.CancelReservationCommand;
import com.example.itonearchitecture.cancel_reservation.CancelReservationHandler;
import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.entity.ReservationStatus;
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
class CancelReservationHandlerTest {

    @Mock
    private ReservationRepository repository;

    @InjectMocks
    private CancelReservationHandler handler;

    @Test
    void handle_ShouldCancelReservation() {
        Long reservationIdValue = 1L;
        ReservationId reservationId = new ReservationId(reservationIdValue);
        TableId tableId = new TableId(2L);
        ReservationTime time = new ReservationTime(LocalDateTime.now());

        Reservation reservation = new Reservation(tableId, time, 4);
        reservation.assignId(reservationId);

        CancelReservationCommand command = new CancelReservationCommand(reservationIdValue);

        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        handler.handle(command);

        verify(repository).save(reservation);
        assertEquals(ReservationStatus.CANCELLED, reservation.getStatus());
    }

    @Test
    void handle_WhenReservationNotFound_ShouldThrowException() {
        Long reservationIdValue = 99L;
        ReservationId reservationId = new ReservationId(reservationIdValue);
        CancelReservationCommand command = new CancelReservationCommand(reservationIdValue);

        when(repository.findById(reservationId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> handler.handle(command));
        verify(repository, never()).save(any());
    }

    @Test
    void handle_WhenReservationAlreadyCancelled_ShouldThrowException() {
        Long reservationIdValue = 1L;
        ReservationId reservationId = new ReservationId(reservationIdValue);
        TableId tableId = new TableId(2L);
        ReservationTime time = new ReservationTime(LocalDateTime.now());

        Reservation reservation = new Reservation(tableId, time, 4);
        reservation.assignId(reservationId);
        reservation.cancel();

        CancelReservationCommand command = new CancelReservationCommand(reservationIdValue);

        when(repository.findById(reservationId)).thenReturn(Optional.of(reservation));

        assertThrows(IllegalStateException.class, () -> handler.handle(command));
        verify(repository, never()).save(any());
    }
}