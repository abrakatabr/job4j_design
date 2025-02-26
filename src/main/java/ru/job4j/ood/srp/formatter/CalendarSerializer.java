package ru.job4j.ood.srp.formatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarSerializer implements JsonSerializer<Calendar> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public JsonElement serialize(Calendar calendar, Type type, JsonSerializationContext context) {
        String formattedDate = dateFormat.format(calendar.getTime());
        return new JsonPrimitive(formattedDate);
    }
}
