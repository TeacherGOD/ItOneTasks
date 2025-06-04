package ru.itone.java_course.teachergod.entity;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {
    @NotNull
    private String name;
    @NotNull
    private Integer age;
    @Builder.Default
    private boolean  sleeping=true;
}
