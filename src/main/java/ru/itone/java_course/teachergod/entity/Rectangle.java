package ru.itone.java_course.teachergod.entity;
import ru.itone.java_course.teachergod.entity.interfaces.Shape;

public record Rectangle(double width, double height) implements Shape {

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Квадрат";
    }

    public Rectangle {
        if (width<=0 || height<=0) {
            throw new IllegalArgumentException("All arguments must be greater than 0 (no other checks)");
        }
    }
}