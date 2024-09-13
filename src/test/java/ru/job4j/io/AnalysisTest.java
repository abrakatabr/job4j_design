package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void whenOk(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("400 12:15:00");
            output.println("500 12:15:50");
            output.println("400 12:16:08");
            output.println("200 12:16:31");
            output.println("200 12:17:00");
            output.println("500 12:17:16");
            output.println("300 12:18:00");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("12:15:00;12:16:31;12:17:16;12:18:00;").hasToString(result.toString());
    }

    @Test
    void whenEmptyResult(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 12:15:00");
            output.println("300 12:15:50");
            output.println("200 12:16:08");
            output.println("200 12:16:31");
            output.println("200 12:17:00");
            output.println("200 12:17:16");
            output.println("300 12:18:00");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat(result).hasSize(0);
    }
}