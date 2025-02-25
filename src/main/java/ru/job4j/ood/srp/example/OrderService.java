package ru.job4j.ood.srp.example;

public class OrderService {
        public void processOrder(Order order) {
            validateOrder(order);
            saveOrderToDatabase(order);
        }

        private void validateOrder(Order order) {
            System.out.println("Order validated.");
        }

        private void saveOrderToDatabase(Order order) {
            System.out.println("Order saved to database.");
        }
}
