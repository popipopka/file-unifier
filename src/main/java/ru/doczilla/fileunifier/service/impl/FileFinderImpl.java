package ru.doczilla.fileunifier.service.impl;

import ru.doczilla.fileunifier.exception.FileFinderException;
import ru.doczilla.fileunifier.model.RelativePath;
import ru.doczilla.fileunifier.service.FileFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileFinderImpl implements FileFinder {
    @Override
    public List<RelativePath> findAll(Path root) throws FileFinderException {
        if (!Files.isDirectory(root)) {
            throw new FileFinderException("Root path is not a directory");
        }

        List<RelativePath> files = new ArrayList<>();

        try (Stream<Path> walker = Files.walk(root)) {
            walker.filter(Files::isRegularFile)
                    .map(root::relativize)
                    .map(relative -> new RelativePath(root, relative))
                    .forEach(files::add);

        } catch (IOException e) {
            throw new FileFinderException("Error while finding files: " + e.getMessage());
        }

        return files;
    }
}