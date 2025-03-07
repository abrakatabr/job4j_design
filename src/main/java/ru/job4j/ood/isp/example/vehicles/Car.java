package ru.job4j.ood.isp.example.vehicles;

public class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Car is driving");
    }

    /**
     * Класс Car реализует метод fly(), который для него не имеет смысла. Это нарушение принципа ISP
     */
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Cars don't fly");
    }
}
