package ru.itone.course_java.core.base_annotations.your_annotation;

public class ControllerImpl implements Controller {

    @Override
    public String post(String header, String body) {
        //something going on here...
        return "response";
    }
}
