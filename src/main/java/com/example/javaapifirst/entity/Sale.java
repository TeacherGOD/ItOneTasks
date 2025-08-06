package com.example.javaapifirst.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Sale {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();

    private String customerId;
    private double total;
    private LocalDateTime date = LocalDateTime.now();
}
