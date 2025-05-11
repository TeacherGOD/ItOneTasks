package ru.itone.course_java.core.base_stream_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class Chest {

    private final List<Loot> content;
    @Getter
    private final ChestLock chestLock;

    public List<Loot> getContent() {
        if (!chestLock.isLocked()) {
            return content;
        } else {
            throw new ChestLockBrokenException();
        }
    }
}
