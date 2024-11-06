package ru.doczilla.fileunifier.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileUtil {
    private FileUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void append(Path file, String content) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
            writer.write(content);
        }
    }

    public static void delete(Path file) throws IOException {
        Files.deleteIfExists(file);
    }

    public static void create(Path file) throws IOException {
        Files.createFile(file);
    }

    public static String read(Path file) throws IOException {
        return Files.readString(file);
    }
}
