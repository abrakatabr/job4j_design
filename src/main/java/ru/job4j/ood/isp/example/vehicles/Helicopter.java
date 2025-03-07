package ru.job4j.ood.isp.example.vehicles;

public class Helicopter implements Vehicle {
    /**
     * Класс Helicopter реализует метод drive(), который для него не имеет смысла. Это нарушение принципа ISP
     */
    @Override
    public void drive() {
        throw new UnsupportedOperationException("Helicopter don't drive");
    }

    @Override
    public void fly() {
        System.out.println("Helicopter is flying");
    }
}
