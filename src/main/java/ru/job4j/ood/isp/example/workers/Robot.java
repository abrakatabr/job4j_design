package ru.job4j.ood.isp.example.workers;

class Robot implements Worker {
    public void work() {
        System.out.println("Robot is working");
    }

    /**
     * интерфейс Worker содержит методы eat() и sleep(), которые не имеют смысла для класса Robot.
     * Это нарушает ISP, так как Robot вынужден реализовывать методы, которые ему не нужны.
     */
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat");
    }

    public void sleep() {
        throw new UnsupportedOperationException("Robots don't sleep");
    }
}
