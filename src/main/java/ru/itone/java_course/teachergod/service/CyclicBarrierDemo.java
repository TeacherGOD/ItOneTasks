package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CyclicBarrier;

@Service
@Slf4j
public class CyclicBarrierDemo {

    public void init(){
        var barrier = new CyclicBarrier(3,()->
                log.info("Все потоки достигли барьера"));
        for (int i=0;i<4;i++) {
            new Thread(new Task(barrier,i)).start();
        }
    }
}
