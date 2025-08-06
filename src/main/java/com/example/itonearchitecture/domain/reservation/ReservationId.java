package com.example.itonearchitecture.domain.reservation;


public record ReservationId(Long value) {
    public ReservationId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Reservation ID must be positive");
        }
    }
}