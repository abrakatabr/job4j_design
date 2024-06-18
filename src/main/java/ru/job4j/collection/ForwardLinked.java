package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {

    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> newNode = new Node<T>(value, null);
        if (size == 0) {
            head = newNode;
        } else {
            Node<T> currentNode = head;
            int count = 1;
            while (count < size) {
                currentNode = currentNode.next;
                count++;
            }
            currentNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> getNode = head;
        for (int i = 0; i < index; i++) {
            getNode = getNode.next;
        }
        return getNode.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T deleted = head.item;
        Node<T> newHead = head.next;
        head.next = null;
        head.item = null;
        head = newHead;
        size--;
        return deleted;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
        modCount++;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> current = head;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Node<T> result = current;
                current = current.next;
                return result.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
