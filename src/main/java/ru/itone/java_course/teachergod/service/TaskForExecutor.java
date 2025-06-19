package ru.itone.java_course.teachergod.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskForExecutor implements Runnable {
    private final int taskId;

    public TaskForExecutor(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        log.info("Задача " + taskId +" начата " +
                Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Задача " + taskId + " завершена");
    }
}
