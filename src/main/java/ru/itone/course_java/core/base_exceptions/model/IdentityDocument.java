package ru.itone.course_java.core.base_exceptions.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentityDocument {

    private DocType type;
    private String code;
    private String number;
    private LocalDate issueDate;
    private LocalDate startDate;
    private LocalDate expireDate;
}
