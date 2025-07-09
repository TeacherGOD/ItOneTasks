package com.example.notesimple.rest.controller;

import com.example.notesimple.business.service.NoteService;
import com.example.notesimple.rest.dto.NoteRequest;
import com.example.notesimple.rest.dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    /**
     * Создаёт новую заметку.
     *
     * @param request объект с данными для создания заметки ({@link NoteRequest})
     * @return {@link ResponseEntity} с созданной заметкой ({@link NoteResponse}) и HTTP статусом 201 Created
     */
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@RequestBody NoteRequest request) {
        NoteResponse response = noteService.createNote(
                request.title(),
                request.content()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Обновляет существующую заметку по её идентификатору.
     *
     * @param noteId идентификатор заметки для обновления
     * @param request объект с новыми данными заметки ({@link NoteRequest})
     * @return {@link ResponseEntity} с обновлённой заметкой ({@link NoteResponse}) и HTTP статусом 200 OK,
     *         или 404 Not Found, если заметка с таким ID не найдена
     */
    @PostMapping("/{note_id}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable("note_id") Long noteId,
            @RequestBody NoteRequest request) {
        NoteResponse response = noteService.updateNote(
                noteId,
                request.title(),
                request.content()
        );
        return response != null
                ? ResponseEntity.ok(response)
                : ResponseEntity.notFound().build();
    }

    /**
     * Получает список всех заметок.
     *
     * @return {@link ResponseEntity} со списком всех заметок ({@link NoteResponse}) и HTTP статусом 200 OK
     */
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }
}
