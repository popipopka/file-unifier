package ru.doczilla.fileunifier.service;

import ru.doczilla.fileunifier.exception.FileFinderException;
import ru.doczilla.fileunifier.model.RelativePath;

import java.nio.file.Path;
import java.util.List;

public interface FileFinder {
    List<RelativePath> findAll(Path root) throws FileFinderException;
}
