package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Comparator<Employee> comparator;

    public ReportHR(Store store, DateTimeParser<Calendar> dateTimeParser, Comparator<Employee> comparator) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.comparator = comparator;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        sortedList.sort(comparator);
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : sortedList) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
