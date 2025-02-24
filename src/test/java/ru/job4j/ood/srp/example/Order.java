package ru.job4j.ood.srp.example;

public class Order {
    private Integer orderNumber;

    public Order(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderNumber=" + orderNumber
                + '}';
    }

    public void sendOrderToThePrinter() {
        System.out.println("The order was printed on a printer");
    }
}
