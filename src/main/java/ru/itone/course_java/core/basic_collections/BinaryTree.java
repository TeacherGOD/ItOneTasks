package ru.itone.course_java.core.basic_collections;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> implements BTree<T> {
    private Node root;

    private class Node {
        T item;
        Node left;
        Node right;

        Node(T item) {
            this.item = item;
        }
    }

    @Override
    public void add(T item) {
        root = addRecursive(root, item);
    }

    private Node addRecursive(Node current, T item) {
        if (current == null) {
            return new Node(item);
        }

        int compare = item.compareTo(current.item);
        if (compare < 0) {
            current.left = addRecursive(current.left, item);
        } else if (compare > 0) {
            current.right = addRecursive(current.right, item);
        }
        return current;
    }

    @Override
    public void remove(T item) {
        root = removeRecursive(root, item);
    }

    private Node removeRecursive(Node current, T item) {
        if (current == null) return null;

        int compare = item.compareTo(current.item);
        if (compare < 0) {
            current.left = removeRecursive(current.left, item);
        } else if (compare > 0) {
            current.right = removeRecursive(current.right, item);
        } else {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            T smallestValue = findMinValue(current.right);
            current.item = smallestValue;
            current.right = removeRecursive(current.right, smallestValue);
        }
        return current;
    }

    private T findMinValue(Node node) {
        return node.left == null ? node.item : findMinValue(node.left);
    }

    @Override
    public boolean contains(T item) {
        return containsRecursive(root, item);
    }

    private boolean containsRecursive(Node current, T item) {
        if (current == null) return false;

        int compare = item.compareTo(current.item);
        if (compare == 0) {
            return true;
        }
        return compare < 0
                ? containsRecursive(current.left, item)
                : containsRecursive(current.right, item);
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.left, list);
            list.add(node.item);
            inOrderTraversal(node.right, list);
        }
    }
}