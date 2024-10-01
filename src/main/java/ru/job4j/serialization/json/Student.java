package ru.job4j.serialization.json;

public class Student {
    private final int age;
    private final boolean isMale;
    private final String name;
    private final Subject subject;

    public Student(int age, boolean isMale, String name, Subject subject) {
        this.age = age;
        this.isMale = isMale;
        this.name = name;
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Student{"
                + "age=" + age
                + ", isMale=" + isMale
                + ", name='" + name + '\''
                + ", subject=" + subject
                + '}';
    }
}
