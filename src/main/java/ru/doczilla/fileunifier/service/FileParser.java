package ru.doczilla.fileunifier.service;

import ru.doczilla.fileunifier.model.RelativePath;

import java.util.List;

public interface FileParser<T> {
    List<T> parse(RelativePath file);
}
