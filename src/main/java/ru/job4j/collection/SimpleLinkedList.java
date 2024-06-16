package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (size == 0) {
            head = newNode;
        } else {
            Node<E> currentNode = head;
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int count = 0;
        Node<E> getNode = head;
        while (count < index) {
            getNode = getNode.next;
            count++;
        }
        return getNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> currentNext = head;
            private final int expectedModCount = modCount;
            private int point;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Node<E> result = currentNext;
                currentNext = currentNext.next;
                point++;
                return result.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
