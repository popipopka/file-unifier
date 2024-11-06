package ru.doczilla.fileunifier.util;

import ru.doczilla.fileunifier.model.RelativePath;
import ru.doczilla.fileunifier.service.FileParser;
import ru.doczilla.fileunifier.service.impl.FileRequireParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceUtil {
    private ServiceUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<RelativePath, List<RelativePath>> buildRequirementsGraph(List<RelativePath> files) {
        FileParser<RelativePath> fileParser = new FileRequireParser();

        Map<RelativePath, List<RelativePath>> graph = new HashMap<>();

        for (RelativePath file : files) {
            graph.put(file, fileParser.parse(file));
        }

        return graph;
    }
}
