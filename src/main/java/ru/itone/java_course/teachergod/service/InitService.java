package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.entity.Person;
import ru.itone.java_course.teachergod.entity.Student;

@Slf4j
@Service
public class InitService {

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    public void showStudents(){
        Person person = new Person("Scott Lima", 30);
        Student student = new Student("Nicole Gomes", 20, "ИНМИТ");

        log.info("1. {}", person);
        log.info("2. {}", student);

        person.setAge(31);
        student.setUniversity("СПбГУ");

        log.info("Обновим сущности:");
        log.info("1. {}", person);
        log.info("2. {}", student);
    }

}
