package ru.itone.java_course.teachergod.entity;
import ru.itone.java_course.teachergod.entity.interfaces.Shape;


public record Circle(double radius) implements Shape {

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Круг";
    }

    public Circle {
        if (radius<=0) {
            throw new IllegalArgumentException("All arguments must be greater than 0 (no other checks)");
        }
    }
}