package ru.itone.java_course.teachergod.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ru.itone.java_course.teachergod.service.InitService;

@Configuration
@RequiredArgsConstructor
public class InitConfig implements CommandLineRunner {

    private final InitService initService;

    @Override
    public void run(String... args){
        initService.logSomeText();
        initService.showImpAndFunc();
    }
}
