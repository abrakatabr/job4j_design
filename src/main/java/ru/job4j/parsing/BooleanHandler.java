package ru.job4j.parsing;

import java.util.regex.Pattern;

class BooleanHandler implements Handler {
    private Pattern pattern;
    private String parameter;

    public BooleanHandler() {
        this.pattern = Pattern.compile("(true)|(false)");
        this.parameter = "булевых данных";
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
