package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.products.Food;

import java.nio.file.Files;

public class Warehouse extends AbstractStore {
    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (percentage(food) < 25) {
            result = getProducts().add(food);
        }
        return result;
    }
}
