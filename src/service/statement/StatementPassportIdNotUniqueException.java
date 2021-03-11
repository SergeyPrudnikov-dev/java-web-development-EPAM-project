package by.epam.jvd.vitebsk.task4.service.statement;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class StatementPassportIdNotUniqueException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = 2451950626367830938L;
    private long entrant_id;

    public StatementPassportIdNotUniqueException(long entrant_id) {
        this.entrant_id = entrant_id;
    }

    public long getEntrant_id() {
        return entrant_id;
    }
}