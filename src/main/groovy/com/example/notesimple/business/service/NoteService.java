package com.example.notesimple.business.service;

import com.example.notesimple.business.entity.Note;
import com.example.notesimple.business.exception.NoteNotFoundException;
import com.example.notesimple.business.repository.NoteRepository;
import com.example.notesimple.rest.dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public Mono<NoteResponse> createNote(String title, String content) {
        Note note = Note.builder()
                .title(title)
                .content(content).build();

        return noteRepository.save(note).map(this::toResponse);
    }

    public Mono<NoteResponse> updateNote(Long id, String title, String content) {
        return noteRepository.findById(id)
                .switchIfEmpty(Mono.error(new NoteNotFoundException(String.format("No note with id: %d",id))))
                .map(note->{
                    note.setTitle(title);
                    note.setContent(content);
                    return noteRepository.save(note);
                })
                .flatMap(mono->
                        mono.map(this::toResponse));
    }

    public Flux<NoteResponse> getAllNotes() {
        return noteRepository.findAll()
                .map(note -> new NoteResponse(note.getId(), note.getTitle(), note.getContent()))
                ;
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(note.getId(), note.getTitle(),note.getContent());
    }
}
