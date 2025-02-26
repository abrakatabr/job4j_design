package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.formatter.CalendarAdapter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @XmlRootElement(name = "employees")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Employees {

        @XmlElement(name = "employee")
        private List<Employee> employees;

        public Employees() { };

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try {
            List<Employee> employeesList = store.findBy(filter);
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(employeesList), writer);
                xml = writer.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
