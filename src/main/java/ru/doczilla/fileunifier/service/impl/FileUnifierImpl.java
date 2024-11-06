package ru.doczilla.fileunifier.service.impl;

import ru.doczilla.fileunifier.exception.FileFinderException;
import ru.doczilla.fileunifier.model.RelativePath;
import ru.doczilla.fileunifier.service.FileUnifier;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static ru.doczilla.fileunifier.model.FileUtil.*;

public class FileUnifierImpl implements FileUnifier {
    @Override
    public void unify(List<RelativePath> files, Path root, String outputFileName) {
        List<Path> absoluteFiles = files.stream()
                .map(RelativePath::getAbsolute)
                .toList();

        Path outputFile = root.resolve(outputFileName);

        try {
            delete(outputFile);
            create(outputFile);

            for (Path file : absoluteFiles) {
                append(outputFile, read(file));
            }

        } catch (IOException e) {
            throw new FileFinderException("Error when merging files: " + e.getMessage());
        }
    }
}
