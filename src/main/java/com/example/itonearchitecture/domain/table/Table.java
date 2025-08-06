package com.example.itonearchitecture.domain.table;

import lombok.Getter;

@Getter
public class Table {
    private final TableId id;
    private final int capacity;

    public Table(TableId id, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Table capacity must be positive");
        }
        this.id = id;
        this.capacity = capacity;
    }
}
