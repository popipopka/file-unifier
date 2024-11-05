package ru.doczilla.fileunifier.model;

import java.nio.file.Path;

public class RelativePath {
    private final Path root;

    private final Path relative;

    public RelativePath(Path root, Path relative) {
        this.root = root;
        this.relative = relative;
    }

    public Path getRoot() {
        return root;
    }

    public Path getRelative() {
        return relative;
    }

    public Path getAbsolute() {
        return root.resolve(relative);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelativePath that = (RelativePath) o;
        return root.equals(that.root) && relative.equals(that.relative);
    }

    @Override
    public int hashCode() {
        int result = root.hashCode();
        result = 31 * result + relative.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return relative.toString();
    }
}
