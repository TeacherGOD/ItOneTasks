package com.example.javaapifirst.controller;

import com.example.javaapifirst.dto.MovementRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Контроллер для управления перемещением товаров между локациями.
 * Обеспечивает операции по перемещению товаров со склада в торговый зал и обратно.
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    /**
     * Перемещает товар между складом и торговым залом.
     *
     * @param dto Запрос на перемещение товара
     * @return Пустой ответ с кодом 200 в случае успеха
     */
    @PostMapping("/movements")
    public ResponseEntity<Void> moveProduct(@RequestBody MovementRequestDTO dto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Логика пока не реализована");
    }
}
