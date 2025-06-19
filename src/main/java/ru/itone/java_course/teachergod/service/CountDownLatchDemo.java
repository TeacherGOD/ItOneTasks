package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class CountDownLatchDemo {
    public void init() throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(150);
        for (int i = 0; i < 30; i++) {
            new Thread(new Worker(startLatch, finishLatch, i)).start();
        }
        Thread.sleep(1000);
        //log.info("Все рабочие запустились");
        startLatch.countDown();
        finishLatch.await();
        log.info("Все рабочие закончили");
    }


}
