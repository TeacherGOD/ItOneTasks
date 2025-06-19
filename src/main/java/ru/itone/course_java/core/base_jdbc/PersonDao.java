package ru.itone.course_java.core.base_jdbc;

import lombok.RequiredArgsConstructor;
import ru.itone.course_java.core.base_jdbc.exceptions.DataAccessException;
import ru.itone.course_java.core.base_jdbc.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        final String sql = "SELECT id, name FROM person";

        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            List<Person> persons = new ArrayList<>();
            while (rs.next()) {
                persons.add(mapToPerson(rs));
            }
            return persons;

        } catch (SQLException e) {
            throw new DataAccessException("Failed to get all persons", e);
        }
    }

    private Person mapToPerson(ResultSet rs) throws SQLException {
        return new Person(
                UUID.fromString(rs.getString("id")),
                rs.getString("name")
        );
    }
}
