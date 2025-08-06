package com.example.itonearchitecture.repository;

import com.example.itonearchitecture.domain.table.Table;
import com.example.itonearchitecture.domain.table.TableId;

import java.util.List;
import java.util.Optional;


public interface TableRepository {
    Optional<Table> findById(TableId id);
    Table save(Table table);
    List<Table> findByCapacityGreaterThanEqual(int capacity);
}
