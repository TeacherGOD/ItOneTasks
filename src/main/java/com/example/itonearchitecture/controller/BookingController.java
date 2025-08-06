package com.example.itonearchitecture.controller;

import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Reservation bookTable(
            @RequestParam Long tableId,
            @RequestParam LocalDateTime dateTime,
            @RequestParam int guests) {
        return bookingService.bookTable(tableId, dateTime, guests);
    }
}