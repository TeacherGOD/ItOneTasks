package ru.itone.course_java.core.base_exceptions;

import ru.itone.course_java.core.base_exceptions.model.DocType;
import ru.itone.course_java.core.base_exceptions.model.IdentityDocument;
import ru.itone.course_java.core.base_exceptions.model.Person;
import ru.itone.course_java.core.base_exceptions.model.Sex;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class BaseExceptions {

    //@formatter:off
    /**
     * Реализовать проверку объекта класса {@link Person} по следующим правилам, симулируем работу электронного КПП:
     * -------------------------------
     * - У человека обязательно должно быть указано Имя
     *      - Бросить ошибку с текстом "Имя человека является обязательным параметром"
     * - Имя, Фамилия, Отчество и Дополнительное Имя (MiddleName) должны состоять только из латинских букв, кириллицы, пробела, точки, тире -, одинарной кавычки ' и апострофа `
     *      - Бросить ошибку с текстом "ФИО и Дополнительное Имя может состоять только из латиницы, кириллицы, пробела, точки, тире, одинарной кавычки и апострофа, символ %s не валидный",
     *        где вместо %s указать символ, который не прошёл проверку
     * - Если у человека есть Отчество (patronymic) то у него не должно быть указан MiddleName и наоборот
     *      - Бросить ошибку с текстом "Должно быть указано либо только Отчество, либо только Дополнительное Имя"
     * - Возраст человека должен быть больше нуля и не больше 120
     *      - Бросить ошибку с текстом "Возраст человека должен быть больше 0 и не больше 120 лет, указанный возраст %d",
     *        где вместо %d указать возраст, который не прошёл проверку
     * - Всегда должен быть указан пол {@link Sex} человека
     *      - Бросить ошибку с текстом "Пол является обязательным параметром"
     * - У человека всегда должен быть паспорт
     *      - Бросить ошибку с текстом "Требуется паспорт"
     * - Тип паспорта указывается всегда
     *      - Бросить ошибку с текстом "Тип паспорта должен быть указан"
     * - Поле resident=true означает, что человек гражданин страны, поэтому у него должен быть паспорт {@link IdentityDocument} типа {@link DocType#PASSPORT}
     *      - Бросить ошибку с текстом "Гражданство и тип паспорта должны совпадать, указано гражданин: %s, тип паспорта: %s",
     *        где вместо первой %s указать "Да" или "Нет", а вместо второй "Внутренний паспорт" или "Зарубежный паспорт"
     * - Если resident=false, то человек турист и у человека должен быть {@link IdentityDocument} типа {@link DocType#FOREIGN_PASSPORT}
     *      - Бросить ошибку аналогичную выше
     * - Код паспорта для резидентов должен состоять из 4 цифр, для не резидентов из одной латинской буквы A, B или C
     *      - Бросить ошибку с текстом "Код паспорта для граждан должен состоять из 4 цифр, а для туристов быть A,B,C, %s паспорте указан код: %s",
     *        где вместо первой %s указать "во внутреннем" или "в зарубежном", а вместо второй не валидный код
     * - Номер паспорта для резидентов должен состоять из 6 цифр, для не резидентов из 8 ИЛИ 10 цифр
     *      - Бросить ошибку с текстом "Номер паспорта для граждан должен состоять из 6 цифр, а для туристов 8 или 10 цифр, %s паспорте указан номер: %s",
     *        где вместо первой %s указать "во внутреннем " или "в зарубежном", а вместо второй не валидный номер
     * - Дата выдачи паспорта {@link IdentityDocument#getIssueDate()} не должна быть позже даты начала действия паспорта {@link IdentityDocument#getStartDate()}
     *      - Бросить ошибку с текстом "Дата выдачи паспорта %s не может быть позже даты начала действия паспорта %s",
     *        где вместо первой %s указать дату выдачи, а вместо второй дату начала действия паспорта
     * - Дата выдачи паспорта и дата начала действия паспорта не должна быть в будущем
     *      - Бросить ошибку с текстом "Дата выдачи %s и начала действия паспорта %s не должна быть в будущем",
     *        где вместо первой %s указать дату выдачи, а вместо второй дату начала действия паспорта
     * - Дата окончания действия паспорта не должна быть в прошлом, но может быть сегодня
     *      - Бросить ошибку с текстом "Паспорт должен быть действительным, срок действия паспорта подошёл к концу %s",
     *        где вместо %s указать дату окончания действия паспорта
     * -------------------------------
     * Для бросания ошибок создайте свой собственные классы ошибок, разделите их по доменам, ошибка данных пользователя и ошибка паспорта
     * Унаследуйте ошибки от {@link RuntimeException}
     *
     * @param person Проверяемые данные
     */
    //@formatter:on
    public void checkPerson(Person person) {
        throw new UnsupportedOperationException();
    }

    /**
     * Используйте этот метод для получения текущей даты,
     * чтобы избежать проблем с {@link LocalDate#now()},
     * который возвращает дату в вашем текущем поясе (systemDefaultZone()) из-за чего могут возникать проблемы.
     * Тесты также будут использовать этот метод для указания даты в тестовых данных/
     *
     * @return Текущая дата в UTC+0
     */
    public static LocalDate today() {
        return LocalDate.from(Instant.now().atOffset(ZoneOffset.UTC));
    }
}
