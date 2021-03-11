package by.epam.jvd.vitebsk.task4.service.user;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class UserNotExistsException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = 4283269421129987720L;
    private Long id;

    public UserNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
