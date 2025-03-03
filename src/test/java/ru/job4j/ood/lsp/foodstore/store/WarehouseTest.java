package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.products.Food;
import ru.job4j.ood.lsp.foodstore.products.Milk;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    public void whenAdded() {
        Warehouse warehouse = new Warehouse();
        Food milk = new Milk("Prostokvashino", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(1), 95.99, 0);
        List<Food> expected = List.of(new Milk("Prostokvashino", LocalDateTime.now().plusDays(4),
                LocalDateTime.now().minusDays(1), 95.99, 0));
        assertThat(warehouse.add(milk)).isTrue();
        assertThat(warehouse.getProducts()).containsExactlyElementsOf(expected);
    }

    @Test
    public void whenNotAdded() {
        Warehouse warehouse = new Warehouse();
        Food milk = new Milk("Prostokvashino", LocalDateTime.now().plusDays(3),
                LocalDateTime.now().minusDays(1), 95.99, 0);
        assertThat(warehouse.add(milk)).isFalse();
        assertThat(warehouse.getProducts()).isEmpty();
    }
}