package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.products.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (percentage(food) >= 25 && percentage(food) <= 75) {
            result = getProducts().add(food);
        }
        if (percentage(food) > 75 && percentage(food) < 100) {
            food.setDiscount(0.2);
            result = getProducts().add(food);
        }
        return result;
    }
}
