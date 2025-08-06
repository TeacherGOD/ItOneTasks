package com.example.itonearchitecture.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Reservation {
    private Long id;
    private Long tableId;
    private LocalDateTime dateTime;
    private int guests;
    private ReservationStatus status = ReservationStatus.CONFIRMED;

    public Reservation(Long tableId, LocalDateTime dateTime, int guests) {
        this.tableId = tableId;
        this.dateTime = dateTime;
        this.guests = guests;
    }

    public void cancel() {
        if (this.status != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Нельзя отменить бронь, которая уже отменена");
        }
        this.status = ReservationStatus.CANCELLED;
    }
}
