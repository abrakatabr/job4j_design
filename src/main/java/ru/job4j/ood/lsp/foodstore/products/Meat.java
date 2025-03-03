package ru.job4j.ood.lsp.foodstore.products;

import java.time.LocalDateTime;

public class Meat extends Food {

    public Meat(String name, LocalDateTime expireDate, LocalDateTime createDate, double price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
