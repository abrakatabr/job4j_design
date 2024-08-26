package ru.job4j.newcoll.tree;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class TreeUtils<T> {
    public int countNode(Node<T> root) {
        Queue<Node<T>> queue = new LinkedList<>();
        int result = 0;
        if (root == null) {
            throw new IllegalArgumentException("Корень не должен быть null");
        }
        queue.add(root);
        while (queue.size() > 0) {
            Node<T> currentNode = queue.poll();
            result++;
            if (currentNode.getChildren().size() > 0) {
                for (Node<T> node : currentNode.getChildren()) {
                    queue.add(node);
                }
            }
        }
        return  result;
    }

    public Iterable<T> findAll(Node<T> root) {
        Queue<Node<T>> queue = new LinkedList<>();
        List<T> result = new ArrayList<>();
        if (root == null) {
            throw new IllegalArgumentException("Корень не должен быть null");
        }
        queue.add(root);
        while (queue.size() > 0) {
            Node<T> currentNode = queue.poll();
            result.add(currentNode.getValue());
            if (currentNode.getChildren().size() > 0) {
                for (Node<T> node : currentNode.getChildren()) {
                    queue.add(node);
                }
            }
        }
        return result;
    }
}
