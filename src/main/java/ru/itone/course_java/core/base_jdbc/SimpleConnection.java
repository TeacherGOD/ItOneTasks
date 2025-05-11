package ru.itone.course_java.core.base_jdbc;

import java.sql.Connection;

/**
 * Реализуйте класс, который получает на вход url, username и password для подключения к БД,
 * и затем использует их для создания {@link Connection} при вызове единственного публичного метода.
 */
@FunctionalInterface
public interface SimpleConnection {

    /**
     * Метод возвращает {@link Connection} к базе данных
     *
     * @return {@link Connection}
     */
    Connection getConnection();
}
