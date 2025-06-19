package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ConcurrentHashMapDemo {
    public void init(){
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        Thread writer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put("ключ" + i, i);
                log.debug("Помещен ключ {} с значением {}", i, i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    log.error("Поток Writer прерван", e);
                    Thread.currentThread().interrupt();
                }
            }
            log.info("Writer поток завершил вставлять 100 записей");
        });

        Thread reader = new Thread(() -> {
            int a=0;
            while (a<12) {
                log.debug("Начало чтения map:");
                map.forEach((k, v) -> log.debug("{}: {}", k, v));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("Reader поток прерван", e);
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                a++;
                log.info("Всего элементов в HashMap: {}",map.size());
            }
            log.info("Закончил чтение Map");
        });

            writer.start();
            reader.start();
    }
}
