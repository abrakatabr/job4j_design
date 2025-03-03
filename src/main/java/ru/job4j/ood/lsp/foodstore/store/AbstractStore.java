package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.products.Food;
import ru.job4j.serialization.json.B;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private List<Food> products = new ArrayList<>();

    @Override
    public List<Food> getProducts() {
        return products;
    }

    protected double percentage(Food food) {
        double result = 0D;
        if (food != null) {
            double shelfLife = Duration.between(food.getCreateDate(), food.getExpireDate()).toMinutes();
            double pastTime = Duration.between(food.getCreateDate(), LocalDateTime.now()).toMinutes();
            double percent = (double) (pastTime / shelfLife) * 100;
            BigDecimal number = new BigDecimal(percent)
                    .setScale(2, RoundingMode.HALF_DOWN);
            result = number.doubleValue();
        } else {
            throw new IllegalArgumentException("Food is null!");
        }
        return result;
    }
}
