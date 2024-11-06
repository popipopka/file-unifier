package ru.doczilla.fileunifier.service;

import ru.doczilla.fileunifier.model.RelativePath;

import java.util.List;

public interface FileSorter {
    List<RelativePath> sort(List<RelativePath> files);
}
