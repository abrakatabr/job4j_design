package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.products.Food;

import java.util.List;

public interface Store {

    boolean add(Food food);
    List<Food> getProducts();
}
