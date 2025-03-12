package ru.job4j.ood.dip.example;

public class AnimalCreator {
    /**
     * Возвращаемое значение метода createAnimal зависит от конкретной реализации, нарушение DIP
     */
    public Bird createAnimal() {
        return new Bird();
    }
}
