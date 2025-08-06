package com.example.itonearchitecture.service;

import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Reservation bookTable(Long tableId, LocalDateTime dateTime, int guests) {
        if (bookingRepository.exists(tableId, dateTime)) {
            throw new IllegalStateException("Table is already booked");
        }
        Reservation reservation = new Reservation(tableId, dateTime, guests);
        return bookingRepository.save(reservation);
    }
}
