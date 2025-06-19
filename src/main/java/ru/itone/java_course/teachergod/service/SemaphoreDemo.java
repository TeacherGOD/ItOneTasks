package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class SemaphoreDemo {
    private static final Semaphore semaphore = new Semaphore(3);
    private AtomicInteger resource = new AtomicInteger(0);

    public void init(){
        for (int i=0; i<21;i++) {
            Thread worker=new Thread(()->
            {
                try {
                    semaphore.acquire();
                    log.info(Thread.currentThread().getName()+" в работе");
                    resource.incrementAndGet();
                    log.info(Thread.currentThread().getName()+", Ресурс: "+resource);
                    Thread.sleep(1000);
                    log.info(Thread.currentThread().getName()+", Ресурс(после секунды): "+resource);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }finally {
                    semaphore.release();
                }
            });
            worker.start();
        }
    }
}
