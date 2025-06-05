package ru.itone.java_course.teachergod.report;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.itone.java_course.teachergod.interfaces.PdfReport;

@Slf4j
@RequiredArgsConstructor
public class MyPdfReport implements PdfReport {
    private final @NotNull String data;
    @Override
    public String generatePdf() {
        log.info("Хорошо, что мне не надо реализовывать сохранение в PDF этого: {}",data);
        return data.substring(0, Math.min(data.length(), 30)).replace(" ","_")+".pdf";
    }
}
