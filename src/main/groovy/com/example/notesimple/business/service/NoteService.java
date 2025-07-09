package com.example.notesimple.business.service;

import com.example.notesimple.business.entity.Note;
import com.example.notesimple.business.exception.NoteNotFoundException;
import com.example.notesimple.business.repository.NoteRepository;
import com.example.notesimple.rest.dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteResponse createNote(String title, String content) {
        Note note = Note.builder()
                .title(title)
                .content(content).build();
        Note savedNote = noteRepository.save(note);
        return toResponse(savedNote);
    }

    public NoteResponse updateNote(Long id, String title, String content) {
        var note =noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.format("No note with id: %d",id)));
        note.setTitle(title);
        note.setContent(content);
        noteRepository.save(note   );
        return toResponse(note);

    }

    public List<NoteResponse> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(note -> new NoteResponse(note.getId(), note.getTitle(), note.getContent()))
                .toList();
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(note.getId(), note.getTitle(),note.getContent());
    }
}
