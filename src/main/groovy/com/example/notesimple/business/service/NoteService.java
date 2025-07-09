package com.example.notesimple.business.service;

import com.example.notesimple.business.entity.Note;
import com.example.notesimple.business.exception.NoteNotFoundException;
import com.example.notesimple.rest.dto.NoteResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public NoteResponse createNote(String title, String content) {
        Long id = idCounter.getAndIncrement();
        Note note = new Note(id, title, content);
        notes.put(id, note);
        return toResponse(note);
    }

    public NoteResponse updateNote(Long id, String title, String content) {
        if (notes.containsKey(id)) {
            Note note = notes.get(id);
            note.setTitle(title);
            note.setContent(content);
            return toResponse(note);
        }
        throw new NoteNotFoundException(String.format("No note with id: %d",id));
    }

    public List<NoteResponse> getAllNotes() {
        List<NoteResponse> responseList = new ArrayList<>();
        for (Note note : notes.values()) {
            responseList.add(toResponse(note));
        }
        return responseList;
    }

    private NoteResponse toResponse(Note note) {
        return new NoteResponse(note.getId(), note.getTitle(),note.getContent());
    }
}
