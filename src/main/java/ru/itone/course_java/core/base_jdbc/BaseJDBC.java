package ru.itone.course_java.core.base_jdbc;

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
        throw new UnsupportedOperationException();
    }
}
