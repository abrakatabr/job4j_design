package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleStack;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        return result;
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

    public boolean add(Node<T> root, T parent, T child) {
        boolean result = false;
        if (findByKey(root, parent).isPresent() && findByKey(root, child).isEmpty()) {
            Node<T> currentNode = findByKey(root, parent).get();
            currentNode.getChildren().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        Optional<Node<T>> result = Optional.empty();
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (root == null) {
            throw new IllegalArgumentException("Корень не должен быть null");
        }
        stack.push(root);
        while (stack.getSize() > 0) {
            Node<T> currentNode = stack.pop();
            if (currentNode.getValue().equals(key)) {
                result = Optional.ofNullable(currentNode);
                break;
            }
            if (currentNode.getChildren().size() > 0) {
                for (Node<T> node : currentNode.getChildren()) {
                    stack.push(node);
                }
            }
        }
        return result;
    }

    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        Optional<Node<T>> result = Optional.empty();
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (root == null) {
            throw new IllegalArgumentException("Корень не должен быть null");
        }
        if (root.getValue().equals(key)) {
            result = Optional.ofNullable(root);
        }
        stack.push(root);
        OUTER:
        while (stack.getSize() > 0) {
            Node<T> currentNode = stack.pop();
            for (Node<T> node : currentNode.getChildren()) {
                if (node.getValue().equals(key)) {
                    result = Optional.ofNullable(node);
                    currentNode.getChildren().remove(node);
                    break OUTER;
                }
                stack.push(node);
            }
        }
        return result;
    }
}
