package com.example.javaapifirst.dto;

import java.util.List;
import java.util.UUID;

public record SaleRequestDTO(List<Item> items,
                             UUID customerId) {
    public static class Item {
        private UUID productId;
        private int quantity;
    }
}
