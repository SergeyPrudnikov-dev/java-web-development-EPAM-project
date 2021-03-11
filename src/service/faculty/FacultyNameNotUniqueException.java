package by.epam.jvd.vitebsk.task4.service.faculty;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class FacultyNameNotUniqueException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = 875317782123156578L;
    private String name;

    public FacultyNameNotUniqueException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}