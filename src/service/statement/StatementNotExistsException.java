package by.epam.jvd.vitebsk.task4.service.statement;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class StatementNotExistsException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = -5584854525672681414L;
    private Long id;

    public StatementNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
