package by.epam.jvd.vitebsk.task4.service.enrolled_person;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class EnrolledPersonNotExistsException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = -4477046497333400725L;
    private Long id;

    public EnrolledPersonNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
