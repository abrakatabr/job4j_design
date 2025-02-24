package ru.job4j.ood.srp.example;

public class OrderProcessor {
    public void processOrder(Order order) {
        System.out.println("Order processed.");
    }

    public void sendConfirmationEmail(Order order) {
        System.out.println("Confirmation email sent.");
    }
}
