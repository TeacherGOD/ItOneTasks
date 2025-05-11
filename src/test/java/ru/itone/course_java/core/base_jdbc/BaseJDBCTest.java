package ru.itone.course_java.core.base_jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import ru.itone.course_java.core.base_jdbc.model.Card;
import ru.itone.course_java.core.base_jdbc.model.Person;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Java6Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseJDBCTest {

    BaseJDBC baseJDBC = new BaseJDBC();

    PostgreSQLContainer postgres;
    PersonDao personDao;
    CardDao cardDao;

    @BeforeAll
    void setUp() {
        postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));
        postgres.start();
        try (var conn = DriverManager.getConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword())) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(
                    Files.readString(
                            Paths.get(BaseJDBCTest.class.getClassLoader().getResource("base_jdbc/init.sql").toURI())))) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        personDao = new PersonDao(baseJDBC.createSimpleConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword()));
        cardDao = new CardDao(baseJDBC.createSimpleConnection(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword()));
    }

    @AfterAll
    void tearDown() {
        postgres.stop();
    }

    @Test
    void getAllPeople() {
        assertThat(personDao.getAll()).containsExactlyElementsOf(List.of(
                new Person(UUID.fromString("c1a7025a-3f61-4542-a9a5-2968d406ccab"), "Ivan"),
                new Person(UUID.fromString("999aa405-d7eb-4a6e-88a5-272eaeefdd18"), "Mikhail"),
                new Person(UUID.fromString("14a88c98-d710-49ca-99c2-6c3749311b71"), "Natasha"),
                new Person(UUID.fromString("ca432105-e37a-4e45-bbbd-c92b72d41062"), "Dmitri"),
                new Person(UUID.fromString("8baf0329-3f2e-4823-9460-32a51deab1df"), "Anastasia")
        ));
    }

    @Test
    void findByPersonId() {
        UUID personId = UUID.fromString("14a88c98-d710-49ca-99c2-6c3749311b71");
        assertThat(cardDao.findByPersonId(personId))
                .isEqualTo(new Card(UUID.fromString("263f1d1a-a641-4013-a15f-569615a221e6"), personId, "5555345678901234"));
    }

    @Test
    void findByPersonName() {
        String personName = "Anastasia";
        assertThat(cardDao.findByPersonName(personName))
                .containsExactlyElementsOf(List.of(
                        new Card(UUID.fromString("b0bfc959-76f3-4eab-a759-e5994cbed77a"), UUID.fromString("8baf0329-3f2e-4823-9460-32a51deab1df"), "300578462901457"),
                        new Card(UUID.fromString("04c7ec54-5a89-4b2e-934c-63610c857b60"), UUID.fromString("8baf0329-3f2e-4823-9460-32a51deab1df"), "503896472130548")));
    }
}