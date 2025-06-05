package ru.itone.java_course.teachergod.repository.interfaces;


import ru.itone.java_course.teachergod.entity.Order;

public interface OrderRepository {
    boolean save(Order order);
}
