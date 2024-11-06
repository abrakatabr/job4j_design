package ru.job4j.parsing;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Map<ParseParameter, Handler> handlers = new HashMap<>();

    static {
        handlers.put(ParseParameter.INT, new IntHandler());
        handlers.put(ParseParameter.DOUBLE, new DoubleHandler());
        handlers.put(ParseParameter.STRING, new StringHandler());
        handlers.put(ParseParameter.BOOLEAN, new BooleanHandler());
    }

    public static void countParse(Path path, ParseParameter data) {
        Handler handler = handlers.get(data);
        String parameter = handler.getParameter();
        Pattern pattern = handler.getPattern();
        parse(path, pattern, parameter);
    }

    private static void parse(Path path, Pattern pattern, String parameter) {
        long count = 0;
        try {
            List<String> data = Files.readAllLines(path);
            count = data.stream().filter(s -> {
                Matcher matcher = pattern.matcher(s);
                return matcher.matches();
            }).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Количество %s: %d%s", parameter, count, System.lineSeparator());
    }


    public static void main(String[] args) {
        Path path = Path.of("C:/projects/job4j_design/data/data.txt");
        countParse(path, ParseParameter.INT);
        countParse(path, ParseParameter.DOUBLE);
        countParse(path, ParseParameter.STRING);
        countParse(path, ParseParameter.BOOLEAN);
    }
}
