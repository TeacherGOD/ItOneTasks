package ru.itone.java_course.teachergod.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Order;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InitService {

    private final OrderValidatorService orderValidatorService;
    private final OrderStockAndPackValidatorService orderStockAndPackValidatorService;

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    public void showOrderValidator(){
        var orders= List.of(
            new Order(1L, true, true),
            new Order(2L, true, false),
            new Order(3L, false, false),
            new Order(4L, false, true)
        );
        log.info("Проверка базовым валидатором");
        checkOrder(orders,orderValidatorService);
        log.info("Проверка расширенным валидатором");
        checkOrder(orders,orderStockAndPackValidatorService);
    }

    private void checkOrder(List<@NotNull Order> orders, OrderValidatorService orderValidatorService) {
        orders.forEach(order -> {
            var valid= orderValidatorService.isValid(order);
            if (valid)
                log.info("✅Заказ({}) №{} прошел проверку",order,order.getId());
            else
                log.warn("❌Заказ({}) №{} НЕ прошел проверку",order,order.getId());
        });
    }
}
