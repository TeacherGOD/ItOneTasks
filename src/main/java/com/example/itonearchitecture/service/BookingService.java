package com.example.itonearchitecture.service;

import com.example.itonearchitecture.entity.Reservation;

import java.time.LocalDateTime;

public interface BookingService {
    Reservation bookTable(Long tableId, LocalDateTime dateTime, int guests);
}
