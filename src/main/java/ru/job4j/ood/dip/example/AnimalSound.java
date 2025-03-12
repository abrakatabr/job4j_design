package ru.job4j.ood.dip.example;

public class AnimalSound {
    /**
     * Класс AnimalSound зависит от конкретной реализации (класс Dog) это нарушает DIP
     */
    private Dog dog = new Dog();

    public void makeSound() {
        dog.bark();
    }
}
