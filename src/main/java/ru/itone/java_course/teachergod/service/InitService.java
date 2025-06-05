package ru.itone.java_course.teachergod.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;

import java.util.stream.LongStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {
    private final OrderService orderService;

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    public void showOrderInversion(){
        var orders= LongStream.rangeClosed(1,10)
                .mapToObj(id-> new Order(id, "some random text for order"+id ))
                .toList();
        orders.forEach(order -> {
            var saveResult=orderService.process(order);
            if (saveResult) {
                log.info("✅Заказ сохранен");
            } else {
                log.warn("❌заказ НЕ сохранен");
            }
        });

    }
}
