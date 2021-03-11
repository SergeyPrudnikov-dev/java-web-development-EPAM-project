package by.epam.jvd.vitebsk.task4.di;

public class ServiceFactoryException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ServiceFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceFactoryException(String message) {
        super(message);
    }

    public ServiceFactoryException(Throwable cause) {
        super(cause);
    }

}
