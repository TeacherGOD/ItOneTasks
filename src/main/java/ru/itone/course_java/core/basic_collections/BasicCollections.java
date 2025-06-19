package ru.itone.course_java.core.basic_collections;



public class BasicCollections {

    /**
     * Верните вашу реализацию {@link BTree} в этом методе
     */
    public <T extends Comparable<T>> BTree<T> getNewTree() {
        return new BinaryTree<>();
    }
}
