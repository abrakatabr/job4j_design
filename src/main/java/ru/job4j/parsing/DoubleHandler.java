package ru.job4j.parsing;

import java.util.regex.Pattern;

class DoubleHandler implements Handler {
    private Pattern pattern;
    private String parameter;

    public DoubleHandler() {
        this.pattern = Pattern.compile("(\\d+[\\.,]\\d++)");
        this.parameter = "чисел с плавающей точек";
    }

    @Override
    public Pattern getPattern() {
        return pattern;
    }

    @Override
    public String getParameter() {
        return parameter;
    }
}
