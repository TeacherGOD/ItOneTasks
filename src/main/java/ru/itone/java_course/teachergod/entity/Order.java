package ru.itone.java_course.teachergod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private Long id;
    private boolean inStock;
    private boolean isPacked;
}
