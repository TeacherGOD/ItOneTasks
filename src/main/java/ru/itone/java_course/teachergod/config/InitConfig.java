package ru.itone.java_course.teachergod.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ru.itone.java_course.teachergod.service.*;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitConfig implements CommandLineRunner {

    private final InitService initService;
    private final SharedCounter sharedCounter;
    private final ProducerConsumer producerConsumer;
    private final ReentrantLockDemo reentrantLockDemo;
    private final SemaphoreDemo semaphoreDemo;
    private final CyclicBarrierDemo cyclicBarrierDemo;

    @Override
    public void run(String... args){
        try {

        //initService.logSomeText();
        sharedCounter.init();
        //producerConsumer.init();
        //reentrantLockDemo.init();
        //semaphoreDemo.init();
        cyclicBarrierDemo.init();
        }
        catch (InterruptedException e){
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }

    }
}
