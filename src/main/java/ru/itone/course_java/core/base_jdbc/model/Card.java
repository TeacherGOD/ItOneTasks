package ru.itone.course_java.core.base_jdbc.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Card {

    private UUID id;
    private UUID personId;
    private String number;
}
