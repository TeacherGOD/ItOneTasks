package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

@Service
@Slf4j
public class ProducerConsumer {
    private static final int CAPACITY = 5;
    private final Queue<Integer> buffer = new LinkedList<>();
    private static final int PRODUCERS_COUNT = 2;
    private static final int CONSUMERS_COUNT = 2;
    private final Random r=new Random();

    public void init(){
        for (int i = 0; i < PRODUCERS_COUNT; i++) {
            new Thread(() -> {
                try {
                    produce();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Producer-" + i).start();
        }

        for (int i = 0; i < CONSUMERS_COUNT; i++) {
            new Thread(() -> {
                try {
                    consume();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Consumer-" + i).start();
        }
    }

    public void produce() throws InterruptedException {
        int value = 0;
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (buffer.size() == CAPACITY) {
                    log.info("Буфер полон! " + Thread.currentThread().getName() + " ждёт...");
                    wait();
                }
                buffer.add(value);
                log.info(String.format("%s произвел: %d (Размер буфера: %d)",
                        Thread.currentThread().getName(), value, buffer.size()));
                value++;
                notifyAll();
            }
            Thread.sleep(r.nextLong(10000));
        }
    }

    public void consume() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    log.info("Буфер пуст! " + Thread.currentThread().getName() + " ждёт...");
                    wait();
                }
                int value = buffer.poll();
                log.info(String.format("%s принял: %d (Размер буфера: %d)",
                        Thread.currentThread().getName(), value, buffer.size()));
                notifyAll();
            }
            Thread.sleep(r.nextLong(15000));
        }
    }

}
