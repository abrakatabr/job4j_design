package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.products.Food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores = new ArrayList<>();

    public boolean addStore(Store store) {
        return stores.add(store);
    }

    public void sortFood(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

    public int sortManyProducts(List<Food> products) {
        int count = 0;
        for (Store store : stores) {
            for (Food product : products) {
                if (store.add(product)) {
                    count++;
                }
            }
        }
        return count;
    }
}
