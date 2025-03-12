package ru.job4j.ood.dip.example;

public class AnimalFeeder {
    /**
     * DIP нарушается из-за того, что метод feed(Cat cat) зависит от конкретной реализации
     */
    public void feed(Cat cat) {
        System.out.println("Feeding the cat.");
        cat.meow();
    }
}
