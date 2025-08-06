package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.domain.table.Table;
import com.example.itonearchitecture.domain.table.TableId;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class TableRepositoryImpl implements TableRepository {
    private static final Set<Table> tables = new HashSet<>();

    static {
        tables.add(new Table(new TableId(1L), 4));
        tables.add(new Table(new TableId(2L), 6));
        tables.add(new Table(new TableId(3L), 8));
        tables.add(new Table(new TableId(4L), 10));
    }

    @Override
    public Optional<Table> findById(TableId id) {
        return tables.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public Table save(Table table) {
        tables.add(table);
        return table;
    }

    @Override
    public List<Table> findByCapacityGreaterThanEqual(int capacity) {
        return tables.stream()
                .filter(table -> table.getCapacity() >= capacity)
                .toList();
    }
}
