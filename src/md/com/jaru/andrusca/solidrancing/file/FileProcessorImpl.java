package md.com.jaru.andrusca.solidrancing.file;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessorImpl  implements  FileProcessor{


    @Override
    public boolean openDirectoryStream(Path dirPath) {
        return true;
    }
}
