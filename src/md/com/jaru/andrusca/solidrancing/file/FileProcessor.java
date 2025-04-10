package md.com.jaru.andrusca.solidrancing.file;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.List;

public interface FileProcessor {
    public DirectoryStream<Path> openDirectoryStream(Path dirPath);

    List<String> readFile(Path filePath);

    void write(Path filePath, List<String> content);

    Path createPathWithNewExtension(Path dirPath, String extension);



}
