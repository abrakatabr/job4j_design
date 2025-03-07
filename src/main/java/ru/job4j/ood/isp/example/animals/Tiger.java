package ru.job4j.ood.isp.example.animals;

public class Tiger implements Animal {
    @Override
    public void eat() {
        System.out.println("Tiger is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Tiger is sleeping");
    }

    @Override
    public void hunt() {
        System.out.println("Tiger is hunting");
    }
}
