package com.example.notesimple.rest.controller;

import com.example.notesimple.business.exception.NoteNotFoundException;
import com.example.notesimple.business.service.NoteService;
import com.example.notesimple.rest.dto.NoteRequest;
import com.example.notesimple.rest.dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    /**
     * Создаёт новую заметку.
     *
     * @param request объект с данными для создания заметки ({@link NoteRequest})
     * @return {@link Mono} с созданной заметкой ({@link NoteResponse}).
     *         HTTP статус 201 Created возвращается благодаря аннотации {@link ResponseStatus}.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<NoteResponse> createNote(@RequestBody NoteRequest request) {
        return noteService.createNote(request.title(), request.content());
    }

    /**
     * Обновляет существующую заметку по её идентификатору.
     *
     * @param noteId идентификатор заметки для обновления
     * @param request объект с новыми данными заметки ({@link NoteRequest})
     * @return {@link Mono} с {@link ResponseEntity}, содержащим обновлённую заметку ({@link NoteResponse}) и HTTP статусом 200 OK,
     *         или с HTTP статусом 404 Not Found, если заметка с таким ID не найдена.
     */
    @PostMapping("/{note_id}")
    public Mono<ResponseEntity<NoteResponse>> updateNote(
            @PathVariable("note_id") Long noteId,
            @RequestBody NoteRequest request) {
        return  noteService.updateNote(
                noteId,
                request.title(),
                request.content()
        ).map(ResponseEntity::ok)
                .onErrorResume(NoteNotFoundException.class, ex ->
                        Mono.just(ResponseEntity.notFound().build()));
    }

    /**
     * Получает поток всех заметок.
     *
     * @return {@link Flux} с объектами заметок ({@link NoteResponse}).
     *         HTTP статус 200 OK возвращается по умолчанию.
     */
    @GetMapping
    public Flux<NoteResponse> getAllNotes() {
        return noteService.getAllNotes();
    }
}
