package ru.itone.java_course.teachergod.service;

import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;

@Service
public class OrderStockAndPackValidatorService extends OrderValidatorService{

    @Override
    public boolean isValid(Order order) {
        return super.isValid(order) && order.isPacked();
    }
}
