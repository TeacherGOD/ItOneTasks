package com.example.itonearchitecture.controller;

import com.example.itonearchitecture.dto.BookTableRequest;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.use_case.BookTableUseCase;
import com.example.itonearchitecture.use_case.GetReservationDetailsUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookTableUseCase bookTableUseCase;
    private final GetReservationDetailsUseCase getReservationDetailsUseCase;

    public BookingController(
            BookTableUseCase bookTableUseCase,
            GetReservationDetailsUseCase getReservationDetailsUseCase
    ) {
        this.bookTableUseCase = bookTableUseCase;
        this.getReservationDetailsUseCase = getReservationDetailsUseCase;
    }

    @PostMapping
    public ReservationResponse bookTable(@RequestBody BookTableRequest request) {
        return bookTableUseCase.execute(request);
    }

    @GetMapping("/{id}")
    public ReservationResponse getReservation(@PathVariable Long id) {
        return getReservationDetailsUseCase.execute(id);
    }
}