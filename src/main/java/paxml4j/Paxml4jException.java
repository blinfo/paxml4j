package paxml4j;

/**
 *
 * @author hakan
 */
public class Paxml4jException extends RuntimeException {

    public Paxml4jException() {
    }

    public Paxml4jException(String message) {
        super(message);
    }

    public Paxml4jException(String message, Throwable cause) {
        super(message, cause);
    }

    public Paxml4jException(Throwable cause) {
        super(cause);
    }
}
