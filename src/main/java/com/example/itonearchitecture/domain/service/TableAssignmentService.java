package com.example.itonearchitecture.domain.service;

import com.example.itonearchitecture.domain.reservation.ReservationTime;
import com.example.itonearchitecture.domain.table.Table;
import com.example.itonearchitecture.domain.table.TableId;
import com.example.itonearchitecture.repository.ReservationRepository;
import com.example.itonearchitecture.repository.TableRepository;

import java.util.List;
import java.util.Optional;

public class TableAssignmentService {
    private final TableRepository tableRepository;
    private final ReservationRepository reservationRepository;

    public TableAssignmentService(
            TableRepository tableRepository,
            ReservationRepository reservationRepository
    ) {
        this.tableRepository = tableRepository;
        this.reservationRepository = reservationRepository;
    }

    public Optional<TableId> findAvailableTable(int guests, ReservationTime time) {
        List<Table> tables = tableRepository.findByCapacityGreaterThanEqual(guests);

        for (Table table : tables) {
            if (!reservationRepository.existsByTableAndTime(table.getId(), time)) {
                return Optional.of(table.getId());
            }
        }

        return Optional.empty();
    }

    public boolean isTableAvailable(TableId tableId, ReservationTime time) {
        return !reservationRepository.existsByTableAndTime(tableId, time);
    }
}
