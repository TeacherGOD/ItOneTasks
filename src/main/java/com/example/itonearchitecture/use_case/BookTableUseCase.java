package com.example.itonearchitecture.use_case;

import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.service.TableAssignmentService;
import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.dto.BookTableRequest;
import com.example.itonearchitecture.dto.ReservationResponse;
import com.example.itonearchitecture.entity.Reservation;
import com.example.itonearchitecture.repository.ReservationRepository;

public class BookTableUseCase {
    private final ReservationRepository repository;
    private final TableAssignmentService tableAssignmentService;

    public BookTableUseCase(
            ReservationRepository repository,
            TableAssignmentService tableAssignmentService
    ) {
        this.repository = repository;
        this.tableAssignmentService = tableAssignmentService;
    }

    public ReservationResponse execute(BookTableRequest request) {
        ReservationTime reservationTime = new ReservationTime(request.dateTime());

        TableId tableId = tableAssignmentService.findAvailableTable(request.guests(), reservationTime)
                .orElseThrow(() -> new IllegalStateException("No available table"));

        Reservation reservation = new Reservation(tableId, reservationTime, request.guests());

        Reservation saved = repository.save(reservation);

        return new ReservationResponse(
                saved.getId().value(),
                saved.getTableId().value(),
                saved.getReservationTime().value(),
                saved.getGuests(),
                saved.getStatus()
        );
    }
}
