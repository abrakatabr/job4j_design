package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(path))) {
            reader.lines()
                    .filter(s -> s.length() > 0 && s.charAt(0) != '#')
                    .forEach(s -> {
                        if (s.charAt(0) == '=' || s.charAt(s.length() - 1) == '='
                        || !s.contains("=")) {
                            throw new IllegalArgumentException("Wrong delimiter");
                        }
                        String[] strings = s.split("=", 2);
                        values.put(strings[0], strings[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}