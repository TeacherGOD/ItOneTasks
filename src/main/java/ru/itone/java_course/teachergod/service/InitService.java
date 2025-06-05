package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itone.java_course.teachergod.report.MyPdfReport;

@Slf4j
@Service
public class InitService {

    public void logSomeText(){
        log.info("это инфо слой");
        log.debug("это дебаг");
        log.warn("чувствуешь опасность? Это предупреждение.");
        log.error("Надеюсь, ты увидешь только такую ошибку.");
    }
    public void showPdfReport(){
        var pdfReport=new MyPdfReport("Short text");
        var pdf=pdfReport.generatePdf();
        log.info("Вот мой пдф: {}",pdf);
    }
}
