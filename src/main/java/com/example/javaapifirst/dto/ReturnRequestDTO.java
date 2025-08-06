package com.example.javaapifirst.dto;

import com.example.javaapifirst.entity.enums.ReturnReason;

import java.util.List;
import java.util.UUID;

public record ReturnRequestDTO(UUID saleId,
                               List<Item> items) {
    public static class Item {
        private UUID productId;
        private ReturnReason reason;
    }
}
