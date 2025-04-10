package md.com.jaru.andrusca.solidrancing.argument;

public class WrongArgumentException extends RuntimeException {
    public WrongArgumentException() {
    }

    public WrongArgumentException(String message) {
        super(message);
    }

    public WrongArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
