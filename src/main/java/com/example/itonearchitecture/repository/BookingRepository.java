package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository {
    boolean exists(Long tableId, LocalDateTime dateTime);
    Reservation save(Reservation reservation);
}
