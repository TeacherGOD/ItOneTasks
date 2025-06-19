package ru.itone.course_java.core.base_jdbc;

import ru.itone.course_java.core.base_jdbc.exceptions.ConnectionException;

import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseJDBC {

    /**
     * Данный метод возвращает вашу реализацию интерфейса {@link SimpleConnection},
     * которая затем в тестах будет передана в {@link PersonDao} и {@link CardDao}
     *
     * @param url
     * @param username
     * @param password
     * @return
     */
    public SimpleConnection createSimpleConnection(String url, String username, String password) {
        return () -> {
            try {
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new ConnectionException("Ошибка при подключении к БД при попытке подключиться к URL: "+url, e);
            }
        };

    }
}
