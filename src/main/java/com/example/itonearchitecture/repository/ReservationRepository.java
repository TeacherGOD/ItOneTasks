package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.entity.Reservation;

import java.util.Optional;

public interface ReservationRepository {
    boolean existsByTableAndTime(com.example.itonearchitecture.domain.table.TableId tableId,
                                 com.example.itonearchitecture.domain.reservation.ReservationTime reservationTime);
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(ReservationId id);
}

