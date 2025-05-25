package ru.itone.algorithm.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTest {

    public static final int MAX_SIZE = 100_000;
    Random random = new Random();

    @Test
    void noElements() {
        assertThat(new BinarySearch(new int[0]).search(random.nextInt())).isEqualTo(-1);
    }

    @Test
    void oneElement_Found() {
        assertThat(new BinarySearch(new int[]{0}).search(0)).isEqualTo(0);
    }

    @Test
    void oneElement_NotFound() {
        assertThat(new BinarySearch(new int[]{0}).search(-1)).isEqualTo(-1);
    }

    @Test
    void manyElements_Found() {
        var arr = IntStream.generate(random::nextInt)
                .limit(MAX_SIZE)
                .distinct()
                .sorted()
                .toArray();

        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            var index = random.nextInt(arr.length);
            var result = new BinarySearch(arr).search(arr[index]);
            assertThat(result).isEqualTo(index);
        }
    }

    @Test
    void manyElements_NotFound() {
        var arr = IntStream.generate(random::nextInt)
                .limit(MAX_SIZE)
                .distinct()
                .sorted()
                .toArray();
        var set = new HashSet<Integer>();
        for (int i : arr) {
            set.add(i);
        }

        for (int i = 0; i < 100; i++) {
            int search = random.nextInt();
            while (set.contains(search)) {
                search = random.nextInt();
            }
            var result = new BinarySearch(arr).search(search);
            assertThat(result).isEqualTo(-1);
        }
    }
}