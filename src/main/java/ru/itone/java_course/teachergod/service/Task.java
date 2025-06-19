package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class Task implements Runnable{
    private final CyclicBarrier barrier;
    private final int id;

    public Task(CyclicBarrier barrier, int id) {
        this.barrier = barrier;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            log.info("Thread "+id+ " запущен");
            Thread.sleep(1000+ 200L *id);
            log.info("Thread "+id+" достиг барьера");
            barrier.await();
            log.info("Thread "+id+" Продолжил работу");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
