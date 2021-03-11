package by.epam.jvd.vitebsk.task4.service;

public class ServiceException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -3845647182191821280L;

    public ServiceException() {
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
