package ru.itone.course_java.core.base_annotations.model;

import org.junit.jupiter.api.Test;
import ru.itone.course_java.core.base_annotations.BaseAnnotations;
import ru.itone.course_java.core.base_annotations.your_annotation.ControllerImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BaseAnnotationsTest {

    BaseAnnotations baseAnnotations = new BaseAnnotations();

    @Test
    void handleAnnotation() {
        var result = baseAnnotations.handleAnnotation(List.of(new MockService(), new PersonService(), new MailService(), new PersonService(), new MockService()));
        assertThat(result).isEqualTo(List.of(
                "Profiling ru.itone.course_java.core.base_annotations.model.PersonService implements Service,Protected",
                "Profiling MailService",
                "Profiling ru.itone.course_java.core.base_annotations.model.PersonService implements Service,Protected"));
    }

    @Test
    void logControllerPostRequest() {
        PrintStream originalOut = System.out;
        try {
            var baos = new ByteArrayOutputStream();
            var ps = new PrintStream(baos);
            System.setOut(ps);

            var proxy = baseAnnotations.logControllerPostRequest(new ControllerImpl());
            proxy.post("Authroization: Bearer access_token", "{\"data\":\"sample\"}");

            System.out.flush();
            var consoleLog = baos.toString().trim();
            System.setOut(originalOut);
            assertThat(consoleLog).isEqualTo("[Log] header: Authroization: Bearer access_token body: {\"data\":\"sample\"}");
        } finally {
            System.setOut(originalOut);
        }
    }
}