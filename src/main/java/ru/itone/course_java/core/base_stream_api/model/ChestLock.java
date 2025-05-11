package ru.itone.course_java.core.base_stream_api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChestLock {

    private final ChestKey key;
    @Getter
    private boolean locked = true;

    public boolean checkKey(ChestKey key) {
        return this.key == key;
    }

    public void unlock(ChestKey key) {
        if (this.key == key) {
            locked = false;
            return;
        }
        throw new ChestLockBrokenException();
    }
}
