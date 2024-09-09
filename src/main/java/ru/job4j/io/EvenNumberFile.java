package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            Arrays.stream(text.toString().split(System.lineSeparator()))
                    .map(Integer::parseInt)
                    .map(i -> i % 2 == 0)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
