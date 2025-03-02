package ru.job4j.ood.lsp.example;

public class Calculator {
    public int sum(int a, int b) {
        int sum = a + b;
        if (sum < 0) {
            throw new IllegalArgumentException("Сумма меньше ноля!");
        }
        return a + b;
    }
}
