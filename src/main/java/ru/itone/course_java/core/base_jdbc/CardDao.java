package ru.itone.course_java.core.base_jdbc;

import lombok.RequiredArgsConstructor;
import ru.itone.course_java.core.base_jdbc.model.Card;

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
        throw new UnsupportedOperationException();
    }

    /**
     * Метод возвращает все карты человека с указанным именем, считать что все имена в БД уникальные,
     * то есть двух людей с одинаковыми именами нет
     *
     * @param personName Имя человека
     */
    public List<Card> findByPersonName(String personName) {
        throw new UnsupportedOperationException();
    }
}
