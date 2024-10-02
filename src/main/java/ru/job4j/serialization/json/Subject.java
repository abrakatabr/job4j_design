package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "subject")
@XmlAccessorType(XmlAccessType.FIELD)
public class Subject {
    @XmlAttribute
    private String subjectName;
    @XmlElementWrapper(name = "grades")
    @XmlElement(name = "grade")
    private int[] grades;

    public Subject() { }

    public Subject(String subjectName, int[] grades) {
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
