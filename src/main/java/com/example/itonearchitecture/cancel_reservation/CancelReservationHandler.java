package com.example.itonearchitecture.cancel_reservation;

import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.entity.ReservationStatus;

public class CancelReservationHandler {
    private final CancelReservationRepository repository;

    public CancelReservationHandler(CancelReservationRepository repository) {
        this.repository = repository;
    }

    public void handle(CancelReservationCommand command) {
        Reservation reservation = repository.findById(command.getReservationId());

        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found");
        }

        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed reservations can be cancelled");
        }

        reservation.cancel();

        repository.save(reservation);
    }
}
