package com.example.itonearchitecture.cancel_reservation;

import com.example.itonearchitecture.entity.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelReservationRepository {
    Reservation findById(Long id);
    void save(Reservation reservation);
}
