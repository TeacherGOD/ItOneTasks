package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;

@Service
@Slf4j
public class EmailSenderService {
    public void sendConfirmationEmail(Order order) {

        String emailContent = "Подтверждение заказа №" + order.getId() + "\nКомментарий к заказу: " + order.getComment();
        log.info("\nОтправка email на {}: \n{}", order.getCustomerEmail(), emailContent);

    }
}
