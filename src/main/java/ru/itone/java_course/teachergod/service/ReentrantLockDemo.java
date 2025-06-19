package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class ReentrantLockDemo {
    private int counter = 0;
    private static final ReentrantLock lock = new ReentrantLock();

    public void init() throws InterruptedException {
        Thread t1 = new Thread(() -> increment(1000));
        Thread t2 = new Thread(() -> increment(1000));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.info("Значение счетчика:" + counter);
    }

    private void increment(int times) {
        for (int i = 0; i < times; i++) {
            lock.lock();
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        }
    }
}
