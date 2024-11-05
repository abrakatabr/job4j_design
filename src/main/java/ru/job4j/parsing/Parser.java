package ru.job4j.parsing;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public void parse(Path path) {
        int countString = 0;
        int countInt = 0;
        int countDouble = 0;
        try {
            List<String> text = Files.readAllLines(path);
            for (String s : text) {
                Scanner scanner = new Scanner(s).useDelimiter("[\\n\\s]");
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        scanner.nextInt();
                        countInt++;
                    }
                    if (scanner.hasNext("\\d{1}[\\.,]\\d++")) {
                        scanner.next("\\d{1}[\\.,]\\d++");
                        countDouble++;
                    }
                    if (scanner.hasNext("\\D+")) {
                        scanner.next("\\D+");
                        countString++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Количество чисел: %d%s", countInt, System.lineSeparator());
        System.out.printf("Количество чисел с плавающей точкой: %d%s", countDouble, System.lineSeparator());
        System.out.printf("Количество строк: %d%s", countString, System.lineSeparator());
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        Path path = Path.of("C:/projects/job4j_design/data/data.txt");
        parser.parse(path);
    }
}
