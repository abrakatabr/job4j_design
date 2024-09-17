package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, String> map = new HashMap<>();
    private int count;

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(),
                file.getFileName().toString());
        if (map.containsKey(fileProperty) && count == 0) {
            System.out.printf("%s - %fMb%s%s%s", fileProperty.getName(),
                    (double) fileProperty.getSize() / 1000000,
                    System.lineSeparator(), file.toAbsolutePath(), System.lineSeparator());
            count++;
        }
        if (map.containsKey(fileProperty) && count > 0) {
            System.out.println(file.toAbsolutePath());
            count++;
        }
        map.put(fileProperty, file.toAbsolutePath().toString());
        return super.visitFile(file, attributes);
    }
}