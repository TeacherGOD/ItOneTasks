package com.example.javaapifirst.controller;

import com.example.javaapifirst.dto.SaleRequestDTO;
import com.example.javaapifirst.dto.SaleResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Контроллер для обработки операций по продаже товаров.
 * Управляет процессом оформления покупок покупателями.
 */
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    /**
     * Оформляет новую продажу товаров.
     *
     * @param dto Данные о продаже (покупатель и список товаров)
     * @return Детали оформленной продажи
     */
    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO dto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Логика пока не реализована");
    }
}
