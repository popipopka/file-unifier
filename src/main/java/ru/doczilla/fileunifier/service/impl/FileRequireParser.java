package ru.doczilla.fileunifier.service.impl;

import ru.doczilla.fileunifier.exception.FileParserException;
import ru.doczilla.fileunifier.model.RelativePath;
import ru.doczilla.fileunifier.service.FileParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileRequireParser implements FileParser<RelativePath> {
    private static final Pattern PATTERN = Pattern.compile("require\\s+‘(.+)’");

    @Override
    public List<RelativePath> parse(RelativePath file) {
        List<RelativePath> results = new ArrayList<>();

        try {
            Matcher matcher = PATTERN.matcher(Files.readString(file.getAbsolute()));

            while (matcher.find()) {
                String result = matcher.group(1);

                results.add(new RelativePath(file.getRoot(), Path.of(result)));
            }

        } catch (IOException e) {
            throw new FileParserException("Error reading the file: " + e.getMessage());
        }

        return results;
    }
}
