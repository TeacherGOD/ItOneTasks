package com.example.notesimple.business.repository;

import com.example.notesimple.business.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {


}

