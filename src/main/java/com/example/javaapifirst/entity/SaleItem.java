package com.example.javaapifirst.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class SaleItem {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    private Product product;

    private int quantity;
    private double price;
}
