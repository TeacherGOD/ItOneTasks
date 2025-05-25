package ru.itone.algorithm.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

class QuickSortTest {

    static final int MAX_SIZE = 100_000;

    Random random = new Random();

    @Test
    void sort() {
        var arr = IntStream.generate(random::nextInt)
                .limit(MAX_SIZE)
                .distinct()
                .toArray();

        var sorted = new QuickSort(arr).sort();
        for (int i = 1; i < sorted.length; i++) {
            Assertions.assertThat(sorted[i]).isGreaterThanOrEqualTo(sorted[i - 1]);
        }
    }
}