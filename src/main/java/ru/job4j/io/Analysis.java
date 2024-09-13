package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Analysis {
    public void unavailable(String source, String target) {
        StringBuilder result = new StringBuilder();
        AtomicBoolean isBegin = new AtomicBoolean(false);
        try (BufferedReader input = new BufferedReader(new FileReader(source))) {
            input.lines()
                    .forEach(s -> {
                        String[] strings = s.split(" ");
                        if (!isBegin.get() && "400".equals(strings[0])
                                || "500".equals(strings[0])) {
                            result.append(strings[1] + ";");
                            isBegin.set(true);
                        }
                        if (isBegin.get() && !"400".equals(strings[0])
                                && !"500".equals(strings[0])) {
                            result.append(strings[1] + ";" + System.lineSeparator());
                            isBegin.set(false);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)
                ))) {
            output.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
