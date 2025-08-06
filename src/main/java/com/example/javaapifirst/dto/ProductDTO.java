package com.example.javaapifirst.dto;

import com.example.javaapifirst.entity.enums.ProductStatus;

import java.util.UUID;

public record ProductDTO(UUID id,
                         String name,
                         String description,
                         double price,
                         int quantity,
                         ProductStatus productStatus) {
}
