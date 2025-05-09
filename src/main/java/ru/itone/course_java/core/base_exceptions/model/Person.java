package ru.itone.course_java.core.base_exceptions.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private String firstName;
    private String lastName;
    private String middleName;
    private String patronymic;
    private int age;
    private Sex sex;
    private boolean resident;
    private IdentityDocument identityDocument;
}
