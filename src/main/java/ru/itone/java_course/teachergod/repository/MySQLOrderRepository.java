package ru.itone.java_course.teachergod.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.itone.java_course.teachergod.entity.Order;
import ru.itone.java_course.teachergod.repository.interfaces.OrderRepository;

import java.util.Random;

@Repository
@Slf4j
public class MySQLOrderRepository implements OrderRepository {
    private final Random random=new Random();

    //todo починить репозиторий, хотя бы до 75% :D (random.nextDouble() < 0.75)
    @Override
    public boolean save(Order order) {
        log.info("Сохранение заказа №{}",order.getId());
        return random.nextBoolean();
    }
}
