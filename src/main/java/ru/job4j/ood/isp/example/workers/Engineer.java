package ru.job4j.ood.isp.example.workers;

class Engineer implements Worker {
    public void work() {
        System.out.println("Engineer is working");
    }

    public void eat() {
        System.out.println("Engineer is eating");
    }

    public void sleep() {
        System.out.println("Engineer is sleeping");
    }
}
