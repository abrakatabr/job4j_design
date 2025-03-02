package ru.job4j.ood.lsp.example;

class CreditAccount extends Account {
    /**
     * Базовый класс Account требует, чтобы сумма для снятия не превышала баланс.
     * Подтип CreditAccount нарушает это предусловие, разрешая снятие средств даже при недостатке баланса.
     * Это нарушает LSP, потому что подтип изменяет контракт базового класса.
     */
    @Override
    void withdraw(double amount) {
        balance -= amount;
    }
}
