package ru.job4j.ood.lsp.parking.model;

public class Truck extends Car {
    private int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
