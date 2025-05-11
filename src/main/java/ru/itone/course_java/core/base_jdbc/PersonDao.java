package ru.itone.course_java.core.base_jdbc;

import lombok.RequiredArgsConstructor;
import ru.itone.course_java.core.base_jdbc.model.Person;

import java.util.List;

/**
 * ДАО для {@link Person}
 *
 * Структура таблицы:
 *
 * CREATE TABLE person(
 *     id UUID PRIMARY KEY,
 *     name VARCHAR(200)
 * );
 */
@RequiredArgsConstructor
public class PersonDao {

    private final SimpleConnection connection;

    /**
     * Метод возвращает всех людей, которые есть в таблице person
     *
     * @return Список людей
     */
    public List<Person> getAll() {
        throw new UnsupportedOperationException();
    }
}
