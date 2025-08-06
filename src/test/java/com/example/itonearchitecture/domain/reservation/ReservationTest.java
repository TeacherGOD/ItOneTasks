package com.example.itonearchitecture.domain.reservation;

import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.entity.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    @Test
    void createReservation_ValidParameters_Success() {
        TableId tableId = new TableId(1L);
        ReservationTime time = new ReservationTime(LocalDateTime.now().plusHours(1));

        Reservation reservation = new Reservation(tableId, time, 4);

        assertNotNull(reservation);
        assertEquals(4, reservation.getGuests());
        assertEquals(tableId, reservation.getTableId());
    }

    @Test
    void createReservation_TooManyGuests_ThrowsException() {
        TableId tableId = new TableId(1L);
        ReservationTime time = new ReservationTime(LocalDateTime.now().plusHours(1));

        assertThrows(IllegalArgumentException.class, () ->
                new Reservation(tableId, time, 11)
        );
    }

    @Test
    void createReservation_InvalidTime_ThrowsException() {
        LocalDateTime lateTime = LocalDateTime.now().withHour(22).withMinute(0);

        assertThrows(IllegalArgumentException.class, () ->
                new ReservationTime(lateTime)
        );
    }
}