package com.example.itonearchitecture.domain.table;

import java.util.Objects;

public record TableId(Long value) {
    public TableId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("Table ID must be positive");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableId tableId = (TableId) o;
        return Objects.equals(value, tableId.value);
    }

}