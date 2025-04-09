package md.com.jaru.andrusca.solidrancing.file;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface FileProcessor {
    public DirectoryStream<Path> openDirectoryStream(Path dirPath);

}
