package ru.job4j.ood.lsp.parking.model;

public class SimpleCar extends Car {
    private int size = 1;

    @Override
    public int getSize() {
        return this.size;
    }
}
