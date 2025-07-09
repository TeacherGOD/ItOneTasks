package com.example.notesimple.business.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Note {
    private Long id;
    private String title;
    private String content;
}
