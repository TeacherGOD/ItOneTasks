package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SharedCounter {
    private int counter = 0;

    public void init() throws InterruptedException {
        Thread t1 = new Thread(() -> incrementCounter(1000));
        Thread t2 = new Thread(() -> incrementCounter(1000));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.info("Значение счетчика:" + counter);
    }

    private synchronized void incrementCounter(int times) {
        for (int i = 0; i < times; i++) {
            counter++;
        }
    }
}
