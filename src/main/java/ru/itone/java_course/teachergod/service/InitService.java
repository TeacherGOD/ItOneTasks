package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Cat;

@Slf4j
@Service
public class InitService {

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    public void showCat(){
        var cat = Cat.builder()
                .age(20)
                .name("Леонид")
                .build();
        log.info("Вот мой кот: {}",cat);
        log.info(
                cat.isSleeping()
                        ? "К слову, сейчас он спит, как и всегда, а что, ему уже {} лет"
                        : "К слову, сейчас он бодрствует, хотя уже скоро будет ого-го какой возраст ({})",
                cat.isSleeping() ? cat.getAge() : cat.getAge()+1
        );
        //System.out.println(cat);
        //сейчас бы принтовать в  что-то
    }
}
