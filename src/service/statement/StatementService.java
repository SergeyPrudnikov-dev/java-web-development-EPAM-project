package by.epam.jvd.vitebsk.task4.service.statement;

import java.util.List;
import java.util.Map;

import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;

public interface StatementService {
    void save(StatementByEntrant statementEntrant) throws ServiceException;

    void delete(Long id) throws ServiceException;

    StatementByEntrant findById(Long id) throws ServiceException;

    StatementByEntrant findByUserId(Long userId) throws ServiceException;

    List<StatementByEntrant> findAll() throws ServiceException;

    Map<Long, List<StatementByEntrant>> separateStatementsByFaculty(List<StatementByEntrant> statements)
            throws ServiceException;

    void sortByFacultyName(List<StatementByEntrant> statements) throws ServiceException;

    void sortByFullName(List<StatementByEntrant> statements) throws ServiceException;
}
