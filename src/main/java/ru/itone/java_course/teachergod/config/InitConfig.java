package ru.itone.java_course.teachergod.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ru.itone.java_course.teachergod.service.InitService;
import ru.itone.java_course.teachergod.service.ProducerConsumer;
import ru.itone.java_course.teachergod.service.SharedCounter;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitConfig implements CommandLineRunner {

    private final InitService initService;
    private final SharedCounter sharedCounter;
    private final ProducerConsumer producerConsumer;

    @Override
    public void run(String... args){
        try {

        initService.logSomeText();
        sharedCounter.init();
        producerConsumer.init();
        }
        catch (InterruptedException e){
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
