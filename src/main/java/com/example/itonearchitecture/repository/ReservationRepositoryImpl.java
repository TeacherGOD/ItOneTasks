package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.entity.ReservationStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservationRepositoryImpl implements ReservationRepository {
    private final Set<Reservation> reservations = new HashSet<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public boolean existsByTableAndTime(Long tableId, LocalDateTime dateTime) {
        return reservations.stream()
                .anyMatch(r ->
                        Objects.equals(r.getTableId(), tableId) &&
                                r.getDateTime().equals(dateTime) &&
                                r.getStatus() == ReservationStatus.CONFIRMED
                );
    }

    @Override
    public Reservation save(Reservation reservation) {
        if (reservation.getId() == null) {
            reservation.setId(idGenerator.getAndIncrement());
        }
        reservations.add(reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservations.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }
}
