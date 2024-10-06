package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {
    @XmlAttribute
    private int age;
    @XmlAttribute
    private boolean isMale;
    @XmlAttribute
    private String name;
    private Subject subject;

    public Student() { }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
