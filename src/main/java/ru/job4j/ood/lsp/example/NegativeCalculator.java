package ru.job4j.ood.lsp.example;

class NegativeCalculator extends Calculator {
    /**
     * Нарушение постусловия: результат метода sum() может быть отрицательным, хотя в классе-родителе
     * это является ограничением.
     */
    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
