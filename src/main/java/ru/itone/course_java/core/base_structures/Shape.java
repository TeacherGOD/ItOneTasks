package ru.itone.course_java.core.base_structures;

public interface Shape {

    double getArea();

    double getPerimeter();

    default ShapeType getShapeType() {
        return ShapeType.UNDEFINED;
    }
}
