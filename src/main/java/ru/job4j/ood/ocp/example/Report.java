package ru.job4j.ood.ocp.example;

public class Report {
    public void generateReport(String type) {
        if (type.equals("PDF")) {
            System.out.println("Генерация PDF-отчета");
        } else if (type.equals("HTML")) {
            System.out.println("Генерация HTML-отчета");
        }
    }
}
