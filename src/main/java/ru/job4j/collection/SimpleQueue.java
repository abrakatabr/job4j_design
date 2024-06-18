package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        for (int i = 1; i < size; i++) {
            output.push(input.pop());
        }
        T result = input.pop();
        for (int i = 1; i < size; i++) {
            input.push(output.pop());
        }
        size--;
        return result;
    }

    public void push(T value) {
        input.push(value);
        size++;
    }
}
