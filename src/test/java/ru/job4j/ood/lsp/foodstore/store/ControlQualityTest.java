package ru.job4j.ood.lsp.foodstore.store;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.products.Food;
import ru.job4j.ood.lsp.foodstore.products.Meat;
import ru.job4j.ood.lsp.foodstore.products.Milk;
import ru.job4j.ood.lsp.foodstore.products.Vegetable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    private ControlQuality controlQuality;
    private Store warehouse;
    private Store shop;
    private Store trash;

    @BeforeEach
    private void setUp() {
        this.controlQuality = new ControlQuality();
        this.warehouse = new Warehouse();
        this.shop = new Shop();
        this.trash = new Trash();
        controlQuality.addStore(warehouse);
        controlQuality.addStore(shop);
        controlQuality.addStore(trash);
    }

    @Test
    public void whenAddToWarehouse() {
        Food milk = new Milk("Prostokvashino", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(1), 95.99, 0);
        List<Food> expected = List.of(new Milk("Prostokvashino", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(1), 95.99, 0));
        controlQuality.sortFood(milk);
        assertThat(this.warehouse.getProducts()).containsExactlyElementsOf(expected);
        assertThat(this.shop.getProducts()).isEmpty();
        assertThat(this.trash.getProducts()).isEmpty();
    }

    @Test
    public void whenAddToShop() {
        Food milk = new Milk("Prostokvashino", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(4), 95.99, 0);
        List<Food> expected = List.of(new Milk("Prostokvashino", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(4), 95.99, 0));
        controlQuality.sortFood(milk);
        assertThat(this.shop.getProducts()).containsExactlyElementsOf(expected);
        assertThat(this.warehouse.getProducts()).isEmpty();
        assertThat(this.trash.getProducts()).isEmpty();
    }

    @Test
    public void whenAddToTrash() {
        Food milk = new Milk("Prostokvashino", LocalDateTime.now(),
                LocalDateTime.now().minusDays(4), 95.99, 0);
        List<Food> expected = List.of(new Milk("Prostokvashino", LocalDateTime.now(),
                LocalDateTime.now().minusDays(4), 95.99, 0));
        controlQuality.sortFood(milk);
        assertThat(this.trash.getProducts()).containsExactlyElementsOf(expected);
        assertThat(this.warehouse.getProducts()).isEmpty();
        assertThat(this.shop.getProducts()).isEmpty();
    }

    @Test
    public void whenFoodIsNullThenException() {
        Food food = null;
        assertThatThrownBy(() -> controlQuality.sortFood(food)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Food is null!");
    }

    @Test
    public void whenSortManyProducts() {
        Food milk = new Milk("Prostokvashino", LocalDateTime.now(),
                LocalDateTime.now().minusDays(4), 95.99, 0);
        Food meat = new Meat("Beef", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(4), 95.99, 0);
        Food vegetable = new Vegetable("Potato", LocalDateTime.now().plusDays(100),
                LocalDateTime.now().minusDays(4), 95.99, 0);
        List<Food> products = List.of(milk, meat, vegetable);
        assertThat(controlQuality.sortManyProducts(products)).isEqualTo(3);
        assertThat(this.warehouse.getProducts().size()).isEqualTo(1);
        assertThat(this.shop.getProducts().size()).isEqualTo(1);
        assertThat(this.trash.getProducts().size()).isEqualTo(1);
    }
}