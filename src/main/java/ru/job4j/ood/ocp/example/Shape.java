package ru.job4j.ood.ocp.example;

public class Shape {
    private String type;

    public Shape(String type) {
        this.type = type;
    }

    public void draw() {
        if (type.equals("Circle")) {
            System.out.println("Рисуем круг");
        } else if (type.equals("Square")) {
            System.out.println("Рисуем квадрат");
        }
    }
}
