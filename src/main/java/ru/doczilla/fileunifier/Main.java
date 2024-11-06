package ru.doczilla.fileunifier;

import ru.doczilla.fileunifier.exception.FileFinderException;
import ru.doczilla.fileunifier.model.RelativePath;
import ru.doczilla.fileunifier.service.FileFinder;
import ru.doczilla.fileunifier.service.FileUnifier;
import ru.doczilla.fileunifier.service.impl.FileFinderImpl;
import ru.doczilla.fileunifier.service.impl.FileRequireSorter;
import ru.doczilla.fileunifier.service.impl.FileUnifierImpl;

import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final String USAGE = "./gradlew run --args=\"<root-path> <output-file-name>\"";

    public static void main(String[] args) throws FileFinderException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid arguments. Usage: " + USAGE);
        }

        Path root = Path.of(args[0]);

        FileFinder fileFinder = new FileFinderImpl();
        List<RelativePath> files = fileFinder.findAll(root);

        FileRequireSorter fileSorter = new FileRequireSorter();
        List<RelativePath> sortedFiles = fileSorter.sort(files);
        sortedFiles.forEach(System.out::println);

        String outputFileName = args[1];

        FileUnifier fileUnifier = new FileUnifierImpl();
        fileUnifier.unify(sortedFiles, root, outputFileName);
    }
}
