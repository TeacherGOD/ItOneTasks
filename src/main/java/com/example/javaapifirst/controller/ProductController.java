package com.example.javaapifirst.controller;

import com.example.javaapifirst.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Контроллер для управления товарными позициями.
 * Предоставляет операции по созданию и получению информации о товарах.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    /**
     * Создает новую товарную позицию в системе.
     *
     * @param dto Данные для создания товара
     * @return Созданный товар с присвоенным идентификатором
     */
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO dto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Логика пока не реализована");
    }

    /**
     * Возвращает список всех товаров, доступных в системе.
     *
     * @return Список товарных позиций
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Логика пока не реализована");
    }
}
