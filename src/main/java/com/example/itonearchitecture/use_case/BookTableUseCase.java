package com.example.itonearchitecture.use_case;

import com.example.itonearchitecture.dto.BookTableRequest;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.ReservationRepository;

public class BookTableUseCase {
    private final ReservationRepository repository;

    public BookTableUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public ReservationResponse execute(BookTableRequest request) {
        if (repository.existsByTableAndTime(request.tableId(), request.dateTime())) {
            throw new IllegalStateException("Table is already booked");
        }

        Reservation reservation = new Reservation(
                request.tableId(),
                request.dateTime(),
                request.guests()
        );

        Reservation saved = repository.save(reservation);
        return new ReservationResponse(
                saved.getId(),
                saved.getTableId(),
                saved.getDateTime(),
                saved.getGuests(),
                saved.getStatus()
        );
    }
}
