package ru.job4j.ood.lsp.example;

public class Penguin extends Bird {
    /**
     * Если мы используем Penguin как подтип Bird, то вызов метода fly() приведет к исключению.
     * Это нарушает LSP, потому что подтип (Penguin) не может быть использован вместо базового класса
     * (Bird) без изменения поведения программы.
     */
    @Override
    void fly() {
        throw new RuntimeException("Пингвины не умеют летать");
    }
}
