package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Student student = new Student(37, true, "Alex Pozharov",
                new Subject("Maths", new byte[] {5, 4, 5, 5}));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));
        final String studentJson =
                "{"
                        + "\"age\":37,"
                        + "\"isMale\":true,"
                        + "\"name\":\"Alex Pozharov\","
                        + "\"subject\":{\"subjectName\":\"Maths\",\"grades\":[5,4,5,5]}"
                        + "}";
        final Student studentMod = gson.fromJson(studentJson, Student.class);
        System.out.println(studentMod);
    }
}
