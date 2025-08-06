package com.example.javaapifirst.dto;

import com.example.javaapifirst.entity.enums.Location;

import java.util.UUID;

public record MovementRequestDTO(UUID productId, Location location) {
}
