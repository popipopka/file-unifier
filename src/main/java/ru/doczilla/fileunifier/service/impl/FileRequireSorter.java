package ru.doczilla.fileunifier.service.impl;

import ru.doczilla.fileunifier.exception.CycleException;
import ru.doczilla.fileunifier.model.RelativePath;
import ru.doczilla.fileunifier.service.FileSorter;
import ru.doczilla.fileunifier.util.ServiceUtil;

import java.util.*;

public class FileRequireSorter implements FileSorter {

    @Override
    public List<RelativePath> sort(List<RelativePath> files) {
        Map<RelativePath, List<RelativePath>> graph = ServiceUtil.buildRequirementsGraph(files);

        Set<RelativePath> visited = new HashSet<>();
        Deque<RelativePath> result = new ArrayDeque<>();
        Deque<RelativePath> path = new ArrayDeque<>();

        for (RelativePath node : graph.keySet()) {
            if (!visited.contains(node) && !sortUtil(graph, node, visited, result, path)) {
                throw new CycleException("The cyclic dependence is found: " + path.stream().toList());
            }
        }

        return result.stream().toList();
    }

    private boolean sortUtil(Map<RelativePath, List<RelativePath>> graph, RelativePath node,
                             Set<RelativePath> visited, Deque<RelativePath> result, Deque<RelativePath> path) {

        if (path.contains(node)) {
            path.add(node);
            return false;
        }

        if (visited.contains(node)) {
            return true;
        }

        visited.add(node);
        path.add(node);

        for (RelativePath neighbor : graph.get(node)) {
            if (!sortUtil(graph, neighbor, visited, result, path)) {
                return false;
            }
        }

        path.remove(node);
        result.add(node);

        return true;
    }
}
