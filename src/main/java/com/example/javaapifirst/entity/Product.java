package com.example.javaapifirst.entity;

import com.example.javaapifirst.entity.enums.ProductStatus;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String size;
    private String color;
    private double price;
    private String description;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.ON_WAREHOUSE;
}
