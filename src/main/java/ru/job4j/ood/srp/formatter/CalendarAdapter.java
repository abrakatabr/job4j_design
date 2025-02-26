package ru.job4j.ood.srp.formatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarAdapter extends XmlAdapter<String, Calendar> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public GregorianCalendar unmarshal(String v) {
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(dateFormat.parse(v));
        } catch (ParseException e) {
            e.printStackTrace();
        }
            return calendar;
    }

    @Override
    public String marshal(Calendar v) {
        return dateFormat.format(v.getTime());
    }
}
