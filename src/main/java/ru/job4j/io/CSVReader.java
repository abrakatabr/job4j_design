package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.util.List;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        Map<Integer, Integer> counter = new HashMap<>();
        String[] columns = argsName.get("filter").split(",");
        File file = new File(argsName.get("path"));
        StringBuilder builder = new StringBuilder();
        String sep = System.lineSeparator();
        try (Scanner scanner = new Scanner(file).useDelimiter(String.format("%s|(\\r\\n)", argsName.get("delimiter")))) {
            String sourceColumnsString = scanner.nextLine();
            String[] sourceColumns = sourceColumnsString.split(argsName.get("delimiter"));
            for (int i = 0; i < sourceColumns.length; i++) {
                for (int j = 0; j < columns.length; j++) {
                    if (sourceColumns[i].equals(columns[j])) {
                        counter.put(j, i);
                    }
                }
            }
            for (int k = 0; k < columns.length; k++) {
                if (k != columns.length - 1) {
                    builder.append(columns[k]).append(argsName.get("delimiter"));
                } else {
                    builder.append(columns[k]).append(sep);
                }
            }
            int columnCounter = 0;
            while (scanner.hasNextLine()) {
                String[] strings = scanner.nextLine().split(argsName.get("delimiter"));
                for (int k = 0; k < columns.length; k++) {
                    if (k != columns.length - 1) {
                        builder.append(strings[counter.get(k)]).append(argsName.get("delimiter"));
                    } else {
                        builder.append(strings[counter.get(k)]).append(sep);
                    }
                }
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.print(builder);
            } else {
                try (BufferedWriter output = new BufferedWriter(new FileWriter(new File(argsName.get("out"))))) {
                    String str = builder.toString();
                    output.write(builder.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(ArgsName argsName) {
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Error: source not .csv file");
        }
        if (argsName.get("delimiter").length() > 1) {
            throw new IllegalArgumentException("Error: invalid delimiter");
        }
        if (!argsName.get("out").equals("stdout") && !argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Error: the target is set incorrectly");
        }
        File file = new File(argsName.get("path"));
        if (!file.exists()) {
            throw new IllegalArgumentException("Error: source not found");
        }
        String[] columns = argsName.get("filter").split(",");
        try (Scanner scanner = new Scanner(file)) {
            String sourceColumns = scanner.nextLine();
            for (String column : columns) {
                if (!sourceColumns.contains(column)) {
                    throw new IllegalArgumentException(String.format("Column %s not found in source file", column));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        handle(argsName);
    }
}
