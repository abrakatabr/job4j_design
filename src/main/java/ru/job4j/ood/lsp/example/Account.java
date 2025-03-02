package ru.job4j.ood.lsp.example;

public class Account {
    protected double balance;

    void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Недостаточно средств");
        }
        balance -= amount;
    }
}
