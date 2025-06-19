package ru.itone.course_java.core.base_annotations;

import lombok.SneakyThrows;

import ru.itone.course_java.core.base_annotations.model.Profiling;
import ru.itone.course_java.core.base_annotations.model.Service;
import ru.itone.course_java.core.base_annotations.your_annotation.Controller;
import ru.itone.course_java.core.base_annotations.your_annotation.ControllerImpl;
import ru.itone.course_java.core.base_annotations.your_annotation.MethodLogger;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


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
        return services.stream()
                .map(Service::getClass)
                .filter(aClass -> aClass.isAnnotationPresent(Profiling.class))
                .map(aClass -> {
                    Profiling profiling = aClass.getAnnotation(Profiling.class);
                    if ("DEBUG".equals(profiling.level())) {
                        var interfaces = Arrays.stream(aClass.getInterfaces())
                                .map(Class::getSimpleName)
                                .collect(Collectors.joining(","));
                        return String.format("Profiling %s implements %s", aClass.getName(), interfaces);
                    } else {
                        return "Profiling " + aClass.getSimpleName();
                    }
                }).toList();
    }

    /**
     * Создайте аннотацию с названием MethodLogger, добавьте в неё поле enabled(), которое false по-умолчанию.
     * Сделайте её доступной в Runtime и оставьте возможность ставить её только над типами.
     * <p>
     * Аннотируйте класс {@link ControllerImpl} этой аннотацией и укажите enabled = true
     */
    @SneakyThrows
    public Controller logControllerPostRequest(Controller controller) {
        Class<?> aClass = controller.getClass();
        if (aClass.isAnnotationPresent(MethodLogger.class)) {
            MethodLogger annotation = aClass.getAnnotation(MethodLogger.class);
            if (annotation.enabled()) {
                return (Controller) Proxy.newProxyInstance(
                        aClass.getClassLoader(),
                        new Class[]{Controller.class},
                        (proxy, method, args) -> {
                            System.out.println(String.format("[Log] header: %s body: %s%n", args[0], args[1]));
                            return method.invoke(controller, args);
                        });
            }
        }
        return controller;
    }
}
