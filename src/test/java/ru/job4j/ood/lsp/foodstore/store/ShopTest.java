package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.products.Food;
import ru.job4j.ood.lsp.foodstore.products.Meat;
import ru.job4j.ood.lsp.foodstore.products.Milk;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenAddedWithoutDiscount() {
        Shop shop = new Shop();
        Food milk = new Milk("Prostokvashino", LocalDateTime.now().plusDays(7),
                LocalDateTime.now().minusDays(7), 95.99, 0);
        List<Food> expected = List.of(new Milk("Prostokvashino", LocalDateTime.now().plusDays(7),
                LocalDateTime.now().minusDays(7), 95.99, 0));
        assertThat(shop.add(milk)).isTrue();
        assertThat(shop.getProducts()).containsExactlyElementsOf(expected);
    }

    @Test
    public void whenAddedWithDiscount() {
        Shop shop = new Shop();
        Food meat = new Meat("Beef", LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusDays(8), 899.99, 0);
        List<Food> expected = List.of(new Meat("Beef", LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusDays(8), 899.99, 0.2));
        assertThat(shop.add(meat)).isTrue();
        assertThat(shop.getProducts()).containsExactlyElementsOf(expected);
    }

    @Test
    public void whenNotAdded() {
        Shop shop = new Shop();
        Food meat = new Meat("Beef", LocalDateTime.now(),
                LocalDateTime.now().minusDays(8), 899.99, 0);
        assertThat(shop.add(meat)).isFalse();
        assertThat(shop.getProducts()).isEmpty();
    }
}