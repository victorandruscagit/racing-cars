package md.com.jaru.andrusca.solidrancing.file;

import java.io.IOException;

public class FileProcessingException extends RuntimeException {
    public FileProcessingException() {
    }

    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
