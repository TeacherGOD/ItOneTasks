package com.example.itonearchitecture.cancel_reservation;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.entity.ReservationStatus;
import com.example.itonearchitecture.repository.ReservationRepository;

public class CancelReservationHandler {
    private final ReservationRepository repository;

    public CancelReservationHandler(ReservationRepository repository) {
        this.repository = repository;
    }

    public void handle(CancelReservationCommand command) {
        ReservationId reservationId = new ReservationId(command.getReservationId());

        Reservation reservation = repository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed reservations can be cancelled");
        }

        reservation.cancel();
        repository.save(reservation);
    }
}
