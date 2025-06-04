package ru.itone.java_course.teachergod.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student extends Person{
    private String university;


    public Student(String name, int age, String university) {
       setAge(age);
       setName(name);
       setUniversity(university);
    }

    @Override
    public String toString() {
        return "Student(" +
                "name="+getName()+
                ", age="+getAge()+
                ", university='" + university + '\'' +
                ')';
    }
}
