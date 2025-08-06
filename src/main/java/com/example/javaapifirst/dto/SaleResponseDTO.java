package com.example.javaapifirst.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SaleResponseDTO(
        UUID saleId,
        UUID customerId,
        LocalDateTime date,
        double total,
        List<Item> items
) {
    public static class Item {
        private UUID productId;
        private int quantity;
        private double price;
    }

}
