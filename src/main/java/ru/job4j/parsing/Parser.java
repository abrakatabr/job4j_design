package ru.job4j.parsing;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public void parseInt(Path path) {
        long countInt = 0;
        Pattern pattern = Pattern.compile("\\d+");
        try {
            List<String> data = Files.readAllLines(path);
            countInt = data.stream().filter(s -> {
                Matcher matcher = pattern.matcher(s);
                return matcher.matches();
            }).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Количество чисел: %d%s", countInt, System.lineSeparator());
    }

    public void parseDouble(Path path) {
        long countDouble = 0;
        Pattern pattern = Pattern.compile("\\d+[\\.,]\\d++");
        try {
            List<String> data = Files.readAllLines(path);
            countDouble = data.stream().filter(s -> {
                Matcher matcher = pattern.matcher(s);
                return matcher.matches();
            }).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Количество чисел с плавающей точкой: %d%s", countDouble, System.lineSeparator());
    }

    public void parseString(Path path) {
        long countString = 0;
        Pattern pattern = Pattern.compile(".+[^\\.,0-9]+.");
        try {
            List<String> data = Files.readAllLines(path);
            countString = data.stream().filter(s -> {
                Matcher matcher = pattern.matcher(s);
                return matcher.matches();
            }).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Количество строк: %d%s", countString, System.lineSeparator());
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        Path path = Path.of("C:/projects/job4j_design/data/data.txt");
        parser.parseInt(path);
        parser.parseDouble(path);
        parser.parseString(path);
    }
}
