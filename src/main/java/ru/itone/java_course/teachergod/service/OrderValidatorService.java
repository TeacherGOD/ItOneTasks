package ru.itone.java_course.teachergod.service;

import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;

@Service
public class OrderValidatorService {

    public boolean isValid(Order order)
    {
        return order.isInStock();
    }
}
