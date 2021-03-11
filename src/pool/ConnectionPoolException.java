package by.epam.jvd.vitebsk.task4.pool;

public class ConnectionPoolException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
