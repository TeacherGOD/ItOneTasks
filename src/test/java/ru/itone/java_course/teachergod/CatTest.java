package ru.itone.java_course.teachergod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itone.java_course.teachergod.entity.Cat;

class CatTest  {

    @Test
    void testCatEqual(){

        var cat1= Cat.builder()
                .age(10)
                .name("Рыжик")
                .build();
        var cat2=Cat.builder()
                .age(10)
                .name("Рыжик")
                //.sleeping(false)
                .build();
        Assertions.assertEquals(cat1,cat2);
    }
}
