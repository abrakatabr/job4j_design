package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectMain {
    public static void main(String[] args) {
        final Student student = new Student(35, true, "Alex",
                new Subject("Math", new int[]{5, 4, 5}));

        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(4);
        list.add(5);
        JSONArray jsonGrades = new JSONArray(list);
        JSONObject jsonSubject = new JSONObject("{\"subjectName\":\"Math\"}");
        jsonSubject.put("grades", jsonGrades);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", student.getAge());
        jsonObject.put("isMale", student.isMale());
        jsonObject.put("name", student.getName());
        jsonObject.put("subject", jsonSubject);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(student).toString());
    }
}
