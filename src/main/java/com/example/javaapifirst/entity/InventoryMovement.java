package com.example.javaapifirst.entity;

import com.example.javaapifirst.entity.enums.Location;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class InventoryMovement {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private Location fromLocation;

    @Enumerated(EnumType.STRING)
    private Location toLocation;

    private LocalDateTime timestamp = LocalDateTime.now();
}
