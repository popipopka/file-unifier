package ru.doczilla.fileunifier.service.impl;

import ru.doczilla.fileunifier.exception.FileFinderException;
import ru.doczilla.fileunifier.service.FileFinderService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileFinder implements FileFinderService {
    @Override
    public List<File> findAll(String rootPath) throws FileFinderException {
        Path path = Path.of(rootPath);

        if (!Files.isDirectory(path)) {
            throw new FileFinderException("Root path is not a directory");
        }

        List<File> files = new ArrayList<>();

        try (Stream<Path> walker = Files.walk(path)) {
            walker.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(files::add);

        } catch (IOException e) {
            throw new FileFinderException("Error while finding files: " + e.getMessage());
        }

        return files;
    }
}
