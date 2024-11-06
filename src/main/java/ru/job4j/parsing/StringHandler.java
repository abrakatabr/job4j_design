package ru.job4j.parsing;

import java.util.regex.Pattern;

class StringHandler implements Handler {
    private Pattern pattern;
    private String parameter;

    public StringHandler() {
        this.pattern = Pattern.compile("^(?!true|false|\\d++).*");
        this.parameter = "строк";
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
