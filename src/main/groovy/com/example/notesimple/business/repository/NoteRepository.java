package com.example.notesimple.business.repository;

import com.example.notesimple.business.entity.Note;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface NoteRepository extends R2dbcRepository<Note, Long> {


}

