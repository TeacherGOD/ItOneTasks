package com.example.itonearchitecture.domain.reservation;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReservationTime(LocalDateTime value) {
    private static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(22, 0);

    public ReservationTime {
        if (value == null) {
            throw new IllegalArgumentException("Врмя брони не должно быть null");
        }
        LocalTime time = value.toLocalTime();
        if (time.isBefore(OPENING_TIME)) {
            throw new IllegalArgumentException("Ресторан открывается с  10:00");
        }
        if (time.isAfter(CLOSING_TIME.minusMinutes(30))) {
            throw new IllegalArgumentException("Бронирование должно быть не менее 30 минут до закрытия");
        }
    }

}
