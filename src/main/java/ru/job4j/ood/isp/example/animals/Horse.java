package ru.job4j.ood.isp.example.animals;

public class Horse implements  Animal {
    @Override
    public void eat() {
        System.out.println("Horse is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Horse is eating");
    }

    /**
     * Класс Horse реализует метод hunt(), который для него не имеет смысла. Это нарушение принципа ISP
     */
    @Override
    public void hunt() {
        throw new UnsupportedOperationException("Horses don't hunt");
    }
}
