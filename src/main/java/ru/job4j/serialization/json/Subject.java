package ru.job4j.serialization.json;

import java.util.Arrays;

public class Subject {
    private final String subjectName;
    private final byte[] grades;

    public Subject(String subjectName, byte[] grades) {
        this.subjectName = subjectName;
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Subject{"
                + "subjectName='" + subjectName + '\''
                + ", grades=" + Arrays.toString(grades)
                + '}';
    }
}
