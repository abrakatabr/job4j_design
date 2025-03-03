package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.products.Food;

public class Trash extends AbstractStore {

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (percentage(food) >= 100) {
            result = getProducts().add(food);
        }
        return result;
    }
}
