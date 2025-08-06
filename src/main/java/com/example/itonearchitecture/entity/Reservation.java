package com.example.itonearchitecture.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Reservation {
    private Long id;
    private Long tableId;
    private LocalDateTime dateTime;
    private int guests;

    public Reservation(Long tableId, LocalDateTime dateTime, int guests) {
        this.tableId = tableId;
        this.dateTime = dateTime;
        this.guests = guests;
    }
}
