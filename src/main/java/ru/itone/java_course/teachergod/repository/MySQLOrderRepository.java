package ru.itone.java_course.teachergod.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.itone.java_course.teachergod.entity.Order;

@Repository
@Slf4j
public class MySQLOrderRepository {

    public void save(Order order){
        log.info("Сохранение заказа №{}, используя MSQL (прошу, нет. Только PostgreSql)", order.getId());
    }
}
