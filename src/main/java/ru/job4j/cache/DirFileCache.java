package ru.job4j.cache;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Optional;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        validateDir(cachingDir);
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String filePath = cachingDir + "/" + key;
        validate(filePath);
        String value = null;
        try {
            value = Files.readString(Path.of(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private void validate(String key) {
        if (!Files.exists(Path.of(key))) {
            throw new IllegalArgumentException("Файл не существует.");
        }
    }

    private void validateDir(String cachingDir) {
        if (!Path.of(cachingDir).toFile().isDirectory()) {
            throw new IllegalArgumentException("Директории не существуетю");
        }
    }
}
