package com.example.javaapifirst.dto;

import com.example.javaapifirst.entity.enums.ReturnReason;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ReturnResponseDTO(UUID returnId,
                                UUID saleId,
                                LocalDateTime returnDate,
                                ReturnStatus status,
                                List<ReturnItemResponse> items) {
    public record ReturnItemResponse(
            UUID productId,
            ReturnReason reason,
            String itemStatus
    ) {}
}
