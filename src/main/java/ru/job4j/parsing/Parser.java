package ru.job4j.parsing;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static void countParse(Path path, ParseParameter data) {
        String parameter;
        Pattern pattern;
        switch (data) {
            case STRING -> {
                pattern = Pattern.compile("^(?!true|false|\\d++).*");
                parameter = "строк";
            }
            case INT -> {
                pattern = Pattern.compile("\\d+");
                parameter = "чисел";
            }
            case DOUBLE -> {
                pattern = Pattern.compile("(\\d+[\\.,]\\d++)");
                parameter = "чисел с плавающей точек";
            }
            case BOOLEAN -> {
                pattern = Pattern.compile("(true)|(false)");
                parameter = "булевых данных";
            }
            default -> {
                System.out.println("Попробуйте еще раз");
                return;
            }
        }
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
