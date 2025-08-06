package com.example.itonearchitecture.use_case;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.repository.ReservationRepository;

public class GetReservationDetailsUseCase {
    private final ReservationRepository repository;

    public GetReservationDetailsUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public ReservationResponse execute(Long reservationId) {
        return repository.findById(new ReservationId(reservationId))
                .map(reservation -> new ReservationResponse(
                        reservation.getId().value(),
                        reservation.getTableId().value(),
                        reservation.getReservationTime().value(), // исправлено
                        reservation.getGuests(),
                        reservation.getStatus()
                ))
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));
    }
}