package ru.itone.java_course.teachergod.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;
import ru.itone.java_course.teachergod.repository.MySQLOrderRepository;

//Ага, фасад.
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProcessor {
    private final MySQLOrderRepository orderRepository;
    private final EmailSenderService emailSenderService;

    public void processOrder(Order order){
        orderRepository.save(order);
        emailSenderService.sendConfirmationEmail(order);
        log.info("Заказ №{} обработан",order.getId());
    }
}
