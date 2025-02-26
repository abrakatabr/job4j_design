package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarSerializer;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.function.Predicate;

public class ReportJSON implements Report {

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson employees = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(GregorianCalendar.class, new CalendarSerializer())
                .create();
        return employees.toJson(store.findBy(filter));
    }
}
