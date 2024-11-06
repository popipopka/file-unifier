package ru.doczilla.fileunifier.service;

import ru.doczilla.fileunifier.model.RelativePath;

import java.nio.file.Path;
import java.util.List;

public interface FileUnifier {
    void unify(List<RelativePath> files, Path root, String outputFileName);
}
