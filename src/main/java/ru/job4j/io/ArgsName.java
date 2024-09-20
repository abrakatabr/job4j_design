package ru.job4j.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(
                    String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] keyValue = arg.split("=", 2);
            values.put(keyValue[0].substring(1), keyValue[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not all arguments are passed to the program");
        }
        if (!args[0].startsWith("-d") || !args[1].startsWith("-e") || !args[2].startsWith("-o")) {
            throw new IllegalArgumentException("The key format is not correct");
        }
        if (args[1].charAt(args[1].indexOf('=') + 1) != '.') {
            throw new IllegalArgumentException("The file extension is entered incorrectly");
        }
        if (!args[2].endsWith(".zip")) {
            throw new IllegalArgumentException("Archive extension entered incorrectly");
        }
        for (String arg : args) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", arg));
            }
        }
        File file = new File(args[0].substring(3));
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile()));
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
