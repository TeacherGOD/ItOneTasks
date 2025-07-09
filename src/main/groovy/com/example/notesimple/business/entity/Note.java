package com.example.notesimple.business.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="note_table")
@Builder
public class Note {
    @Id
    private Long id;
    private String title;
    private String content;
}
