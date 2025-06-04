package ru.itone.java_course.teachergod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private Integer age;

    public void setAge(int age)
    {
        if (age<0) {
            throw new IllegalArgumentException("Возраст должен быть неотрицательным");
        }
        this.age=age;
    }

}
