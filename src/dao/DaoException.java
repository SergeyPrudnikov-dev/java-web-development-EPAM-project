package by.epam.jvd.vitebsk.task4.dao;

public class DaoException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
