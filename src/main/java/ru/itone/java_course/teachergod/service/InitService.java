package ru.itone.java_course.teachergod.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {

    private final OrderProcessor orderProcessor;

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    public void showOrder(){
        var order=new Order(1l,"Levi.Dixon@yahoo.kz","Condimentum consequat vehicula nostra egestas nisi.");

        log.info("получил новый заказ(в фасаде), пора его обработать");
        orderProcessor.processOrder(order);
        log.info("не ошибка и ладно");
    }
}
