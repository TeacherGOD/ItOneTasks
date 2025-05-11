package ru.itone.course_java.core.base_annotations;

import lombok.SneakyThrows;
import ru.itone.course_java.core.base_annotations.model.Profiling;
import ru.itone.course_java.core.base_annotations.model.Service;
import ru.itone.course_java.core.base_annotations.your_annotation.Controller;
import ru.itone.course_java.core.base_annotations.your_annotation.ControllerImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class BaseAnnotations {

    //@formatter:off
    /**
     * В данном методе вам нужно проанализировать наличие аннотации {@link Profiling} над классом,
     * если она есть, то нужно получить значение параметра {@link Profiling#level()},
     * в зависимости от значения сделать следующее:
     * - Если значение level == "DEBUG", то вывести следующую строку: "Profiling %s implements %s",
     *   где вместо первой %s вывести полное название класса (пакет + название),
     *   а вместо второй %s вывести через запятую все его интерфейсы в упрощенном формате (только название интерфейса, без пакета)
     * - Если значение level == "INFO", то вывести следующую строку: "Profiling %s",
     *   где вместо первой %s вывести упрощенное имя класса (без пакета)
     */
    //@formatter:on
    public List<String> handleAnnotation(List<Service> services) {
        throw new UnsupportedOperationException();
    }

    /**
     * Создайте аннотацию с названием MethodLogger, добавьте в неё поле enabled(), которое false по-умолчанию.
     * Сделайте её доступной в Runtime и оставьте возможность ставить её только над типами.
     * <p>
     * Аннотируйте класс {@link ControllerImpl} этой аннотацией и укажите enabled = true
     */
    @SneakyThrows
    public Controller logControllerPostRequest(Controller controller) {
        var controllerClass = controller.getClass();
        for (Annotation annotation : controllerClass.getAnnotations()) {
            if (annotation.annotationType().getSimpleName().equals("MethodLogger")) {
                for (Method annotationField : annotation.getClass().getMethods()) {
                    if (annotationField.getName().equals("enabled")) {
                        if ((boolean) annotationField.invoke(annotation)) {
                            return (Controller) Proxy.newProxyInstance(controllerClass.getClassLoader(), controllerClass.getInterfaces(),
                                    (proxy, method, args) -> {
                                        System.out.println("[Log] header: " + args[0] + " body: " + args[1]);
                                        Object retVal = method.invoke(controller, args);
                                        return retVal;
                                    });
                        }
                    }
                }
            }
        }
        return controller;
    }
}
