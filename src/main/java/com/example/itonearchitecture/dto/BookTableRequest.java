package com.example.itonearchitecture.dto;

import java.time.LocalDateTime;

public record BookTableRequest(Long tableId,
                               LocalDateTime dateTime,
                               int guests
) {

}
