package by.epam.jvd.vitebsk.task4.service.faculty;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class FacultyNotExistsException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = 5426530753302609372L;
    private Long id;

    public FacultyNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
