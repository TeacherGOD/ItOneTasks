package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.domain.reservation.ReservationId;
import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.entity.ReservationStatus;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final Set<Reservation> reservations = new HashSet<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public boolean existsByTableAndTime(TableId tableId, ReservationTime dateTime) {
        return reservations.stream()
                .anyMatch(r ->
                        r.getTableId().equals(tableId) &&
                                r.getReservationTime().equals(dateTime) &&
                                r.getStatus() == ReservationStatus.CONFIRMED
                );
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            reservation.assignId(new ReservationId(idGenerator.getAndIncrement()));
        }
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(ReservationId id) {
        return reservations.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }
}
