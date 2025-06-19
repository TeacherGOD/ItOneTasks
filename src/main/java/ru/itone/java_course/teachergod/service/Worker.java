package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Worker implements Runnable {
    private final CountDownLatch startLatch;
    private final CountDownLatch finishLatch;
    private final int id;

    public Worker(CountDownLatch startLatch, CountDownLatch finishLatch, int id) {
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            log.info("Рабочий " + id +" начал");
            Thread.sleep(2000);
            log.info("Рабочий " + id +" закончен");
            finishLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
