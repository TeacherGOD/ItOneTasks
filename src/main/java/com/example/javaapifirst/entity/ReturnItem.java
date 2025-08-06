package com.example.javaapifirst.entity;

import com.example.javaapifirst.entity.enums.ReturnReason;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class ReturnItem {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    private Product product;

    @Enumerated(EnumType.STRING)
    private ReturnReason reason;
}
