package md.com.jaru.andrusca.solidrancing.file;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileProcessorImpl implements FileProcessor {

    private static String EXTENSION_SEPARATOR = ".";

    @Override
    public DirectoryStream<Path> openDirectoryStream(Path path) {
        try {
            return Files.newDirectoryStream(path);
        } catch (IOException e) {
            throw new FileProcessingException("Error during directory reading: " + path, e);
        }

    }

    @Override
    public List<String> readFile(Path filePath) {
        return List.of();
    }

    public void write(Path path, List<String> lines) {
        try {
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new FileProcessingException("Error during file writing: " + path, e);
        }
    }

    public Path createPathWithNewExtension(Path base, String extension) {
        String fileName = getBaseName(base.getFileName().toString());
        return Path.of(base.getParent().toString(), fileName + extension);
    }

    private String getBaseName(String fileName) {
        int index = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        return index == -1 ? fileName : fileName.substring(0, index);


    }
}
