package ru.job4j.ood.lsp.foodstore.products;

import java.time.LocalDateTime;
import java.util.Objects;

public class Food {

    private String name;
    private LocalDateTime expireDate;
    private LocalDateTime createDate;
    private double price;
    private double discount;

    public Food(String name, LocalDateTime expireDate, LocalDateTime createDate, double price, double discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price - price * discount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Objects.equals(name, food.name)
                && Objects.equals(expireDate.withNano(0), food.expireDate.withNano(0))
                && Objects.equals(createDate.withNano(0), food.createDate.withNano(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate);
    }
}
