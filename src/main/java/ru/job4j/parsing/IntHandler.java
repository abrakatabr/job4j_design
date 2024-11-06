package ru.job4j.parsing;

import java.util.regex.Pattern;

class IntHandler implements Handler {
    private Pattern pattern;
    private String parameter;

    public IntHandler() {
        this.pattern = Pattern.compile("\\d+");
        this.parameter = "чисел";
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
