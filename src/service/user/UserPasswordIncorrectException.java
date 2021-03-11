package by.epam.jvd.vitebsk.task4.service.user;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class UserPasswordIncorrectException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;

    public UserPasswordIncorrectException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
