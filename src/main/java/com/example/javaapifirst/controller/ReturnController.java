package com.example.javaapifirst.controller;

import com.example.javaapifirst.dto.ReturnRequestDTO;
import com.example.javaapifirst.dto.ReturnResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Контроллер для обработки возвратов товаров.
 * Управляет процессом возврата товаров от покупателей.
 */
@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    /**
     * Обрабатывает возврат товаров покупателем.
     *
     * @param dto Данные о возврате (продажа и список возвращаемых товаров)
     * @return Детали обработанного возврата
     */
    @PostMapping
    public ResponseEntity<ReturnResponseDTO> processReturn(@RequestBody ReturnRequestDTO dto) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Логика пока не реализована");
    }
}
