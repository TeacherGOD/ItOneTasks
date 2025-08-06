package com.example.itonearchitecture.entity;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.table.TableId;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Reservation {
    private ReservationId id;
    private final TableId tableId;
    private final ReservationTime reservationTime;
    private int guests;
    private ReservationStatus status = ReservationStatus.CONFIRMED;

    public Reservation(TableId tableId, ReservationTime reservationTime, int guests) {
        if (guests <= 0) {
            throw new IllegalArgumentException("Number of guests must be positive");
        }
        if (guests > 10) {
            throw new IllegalArgumentException("Cannot book for more than 10 guests per table");
        }

        this.tableId = tableId;
        this.reservationTime = reservationTime;
        this.guests = guests;
    }


    public void cancel() {
        if (this.status != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Нельзя отменить бронь, которая уже отменена");
        }
        this.status = ReservationStatus.CANCELLED;
    }

    public LocalDateTime getDateTime() {
        return reservationTime.value();
    }

    public void assignId(ReservationId id) {
        if (this.id != null) {
            throw new IllegalStateException("ID already assigned");
        }
        this.id = id;
    }
}
