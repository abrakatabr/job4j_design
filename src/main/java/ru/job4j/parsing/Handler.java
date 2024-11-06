package ru.job4j.parsing;

import java.util.regex.Pattern;

interface Handler {
    Pattern getPattern();

    String getParameter();
}
