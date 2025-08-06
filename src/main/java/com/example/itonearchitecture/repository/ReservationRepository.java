package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservationRepository {
    boolean existsByTableAndTime(Long tableId, LocalDateTime dateTime);
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(Long id);
}
