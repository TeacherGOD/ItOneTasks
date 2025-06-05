package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Circle;
import ru.itone.java_course.teachergod.entity.Rectangle;
import ru.itone.java_course.teachergod.entity.Triangle;
import ru.itone.java_course.teachergod.entity.interfaces.Shape;

import java.util.List;

@Slf4j
@Service
public class InitService {

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    public void showShapes(){
        var shapes =List.of(
                new Circle(5.0),
                new Rectangle(1.0,2.0),
                new Circle(10.0),
                new Rectangle(1.4,3.2),
                new Triangle(10.0,4.0)
        );

        shapes.forEach(shape -> log.info("Площадь данной фигуры={}, а это, попрошу заметить: {}", shape.area(), shape));

        var totalArea=shapes.stream().mapToDouble(Shape::area).sum();

        log.info("А всего площадь этих фигур = {}",totalArea);
    }
}
