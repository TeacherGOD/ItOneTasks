package ru.itone.java_course.teachergod.entity;
import ru.itone.java_course.teachergod.entity.interfaces.Shape;

public record Triangle(double base, double height) implements Shape {
    @Override
    public double area() {
        return 0.5*base*height;
    }

    @Override
    public String toString() {
        return "Треугольник";
    }

    public Triangle {
        if (base<=0 || height<=0) {
            throw new IllegalArgumentException("All arguments must be greater than 0 (no other checks)");
        }
    }
}
