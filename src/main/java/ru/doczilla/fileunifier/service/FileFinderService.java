package ru.doczilla.fileunifier.service;

import ru.doczilla.fileunifier.exception.FileFinderException;

import java.io.File;
import java.util.List;

public interface FileFinderService {
    List<File> findAll(String directory) throws FileFinderException;
}
