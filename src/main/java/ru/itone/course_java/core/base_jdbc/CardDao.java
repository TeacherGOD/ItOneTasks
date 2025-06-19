package ru.itone.course_java.core.base_jdbc;

import lombok.RequiredArgsConstructor;
import ru.itone.course_java.core.base_jdbc.exceptions.DataAccessException;
import ru.itone.course_java.core.base_jdbc.model.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ДАО для {@link Card}
 *
 * Структура таблицы:
 *
 * CREATE TABLE card(
 *     id UUID PRIMARY KEY,
 *     person_id UUID,
 *     card_number VARCHAR(40)
 * );
 **/
@RequiredArgsConstructor
public class CardDao {

    private final SimpleConnection connection;

    /**
     * Метод возвращает все карты человека с указанным идентификатором
     *
     * @param personId ИД человека
     */
    public List<Card> findByPersonId(UUID personId) {
        final String sql = "SELECT id, person_id, card_number FROM card WHERE person_id = ?";

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, personId);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapToCards(rs);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Не удалось найти карты по идентификатору человека: " + personId, e);
        }
    }

    /**
     * Метод возвращает все карты человека с указанным именем, считать что все имена в БД уникальные,
     * то есть двух людей с одинаковыми именами нет
     *
     * @param personName Имя человека
     */
    public List<Card> findByPersonName(String personName) {
        final String sql = """
            SELECT c.id, c.person_id, c.card_number 
            FROM card c
            JOIN person p ON c.person_id = p.id
            WHERE p.name = ?
            """;

        try (Connection conn = connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, personName);
            try (ResultSet rs = stmt.executeQuery()) {
                return mapToCards(rs);
            }

        } catch (SQLException e) {
            throw new DataAccessException("Не удалось найти карты по имени человека: " + personName, e);
        }
    }

    private List<Card> mapToCards(ResultSet rs) throws SQLException {
        List<Card> cards = new ArrayList<>();
        while (rs.next()) {
            cards.add(new Card(
                    UUID.fromString(rs.getString("id")),
                    UUID.fromString(rs.getString("person_id")),
                    rs.getString("card_number")
            ));
        }
        return cards;
    }
}
