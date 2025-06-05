package ru.itone.java_course.teachergod.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;
import ru.itone.java_course.teachergod.repository.interfaces.OrderRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    public boolean process(Order order){
        log.info("сервис обрабатывает заказ №{}",order.getId());
        return orderRepository.save(order);
    }
}
