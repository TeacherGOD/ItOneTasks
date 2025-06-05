package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Slf4j
@Service
public class InitService {

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }

    //интересный факт: даже обычный stream на около 20+% быстрее(на моём пк, очевидно) на тестах с 10_000 и 100_000, но простота того же parallelStream - приятно.
    public void showImpAndFunc(){

        IntStream.iterate(10,n-> n<=100_000_000, n->n*10).forEach(size->{
            var numbers= IntStream.rangeClosed(1,size).boxed().toList();
            var impTime=showImp(numbers);
            var funcTime=showFunc(numbers);
            var improvementPercent = (int)((1 - (double)Math.min(impTime, funcTime) / Math.max(impTime, funcTime)) * 100);
            log.info(impTime<=funcTime?
                            "✅Императивный vs ❌ Функциональный на размере в {}, что на {}% лучше"
                            :"❌Императивный vs ✅Функциональный на размере в {}, что на {}% лучше"
                    ,size,improvementPercent);
        });
    }

    private long showImp(List<Integer> numbers) {
        log.debug("Императивный стиль:");
        long start = System.nanoTime();
        var evenNumbers=new ArrayList<>();
        for (Integer num : numbers)
            if (num%2==0)
                evenNumbers.add(num);
        long duration = System.nanoTime() - start;
        log.debug("Всего ушло времени: {}",duration);
        return duration;

    }

    private long showFunc(List<Integer> numbers) {

        log.debug("Функциональный стиль:");
        long start = System.nanoTime();
        List<Integer> evenNumbers;
        if (numbers.size()<=100_000)
            evenNumbers=numbers.stream().filter(num-> num%2==0).toList();
        else
            evenNumbers=numbers.parallelStream().filter(num-> num%2==0).toList();
        long duration = System.nanoTime() - start;
        log.debug("Всего ушло времени: {}",duration);
        return duration;
    }
}
