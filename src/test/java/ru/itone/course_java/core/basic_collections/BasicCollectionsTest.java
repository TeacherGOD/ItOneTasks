package ru.itone.course_java.core.basic_collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itone.course_java.core.basic_collections.brew.Wine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BasicCollectionsTest {

    BasicCollections basicCollections = new BasicCollections();
    Random random = new Random();

    BTree<Apple> tree;
    List<Apple> apples;

    @BeforeEach
    void setUp() {
        tree = basicCollections.getNewTree();
        apples = getApples();
        apples.forEach(tree::add);
    }

    @Test
    void tree_AllAdded() {
        apples.forEach(apple -> assertThat(tree.contains(apple)).isTrue());
    }

    @Test
    void tree_NoDuplicates() {
        apples.forEach(tree::add);

        var treeList = tree.toList();
        assertThat(treeList.size()).isEqualTo(apples.size());
    }

    @Test
    void tree_CorrectOrder() {
        int weight = 0;
        var treeList = tree.toList();

        for (Apple apple : treeList) {
            assertThat(apple.weight).isGreaterThanOrEqualTo(weight);
            weight = apple.weight;
        }
    }

    @Test
    void tree_Remove() {
        var remove = new ArrayList<>(apples.stream()
                .limit(500)
                .toList());
        Collections.shuffle(remove);

        remove.forEach(tree::remove);

        assertThat(remove).allMatch(removed -> !tree.contains(removed));
        var treeList = tree.toList();
        assertThat(treeList).doesNotContain(remove.toArray(new Apple[0]));
    }

    private static ArrayList<Apple> getApples() {
        var apples = new ArrayList<>(IntStream.range(0, 1000)
                .mapToObj(i -> new Apple(100 + i))
                .toList());
        Collections.shuffle(apples);
        return apples;
    }

    @Getter
    @AllArgsConstructor
    public static class Apple implements Comparable<Apple> {

        private int weight;

        @Override
        public int compareTo(Apple o) {
            if (o == null) {
                throw new RuntimeException();
            }
            return weight - o.weight;
        }
    }
}