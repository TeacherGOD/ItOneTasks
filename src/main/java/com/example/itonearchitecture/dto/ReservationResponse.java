package com.example.itonearchitecture.dto;

import com.example.itonearchitecture.entity.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(Long id,
                                  Long tableId,
                                  LocalDateTime dateTime,
                                  int guests,
                                  ReservationStatus status) {
}
