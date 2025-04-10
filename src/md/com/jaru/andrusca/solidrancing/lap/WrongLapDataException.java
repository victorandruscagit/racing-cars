package md.com.jaru.andrusca.solidrancing.lap;

public class WrongLapDataException  extends RuntimeException {

    public WrongLapDataException(String message) {
        super(message);
    }

    public WrongLapDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
