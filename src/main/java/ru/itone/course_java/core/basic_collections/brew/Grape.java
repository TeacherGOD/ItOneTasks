package ru.itone.course_java.core.basic_collections.brew;

import java.time.LocalDate;

public class Grape {

    private String sort;
    private float brix;
    private float acidity;
    private LocalDate collected;

    public Grape(String sort, float brix, float acidity, LocalDate collected) {
        this.sort = sort;
        this.brix = brix;
        this.acidity = acidity;
        this.collected = collected;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public float getBrix() {
        return brix;
    }

    public void setBrix(float brix) {
        this.brix = brix;
    }

    public float getAcidity() {
        return acidity;
    }

    public void setAcidity(float acidity) {
        this.acidity = acidity;
    }

    public LocalDate getCollected() {
        return collected;
    }

    public void setCollected(LocalDate collected) {
        this.collected = collected;
    }
}
