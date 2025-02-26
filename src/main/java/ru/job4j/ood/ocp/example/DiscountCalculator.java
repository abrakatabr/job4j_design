package ru.job4j.ood.ocp.example;

import java.util.List;
import java.util.stream.Collectors;

public class DiscountCalculator {
    public List<Double> calculateDiscount(List<WinterDiscount> discounts) {
        return discounts.stream()
                .map(d -> d.getDiscount())
                .collect(Collectors.toList());
    }
}
