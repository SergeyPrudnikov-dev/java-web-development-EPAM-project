package by.epam.jvd.vitebsk.task4.dao;

import java.util.List;

import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;

public interface StatementDao extends Dao<StatementByEntrant> {

    List<StatementByEntrant> readAll() throws DaoException;

    List<StatementByEntrant> readByFaculty(Faculty faculty) throws DaoException;

    StatementByEntrant readBypassportId(String passportId) throws DaoException;

    StatementByEntrant readByUserId(Long userId) throws DaoException;
}
