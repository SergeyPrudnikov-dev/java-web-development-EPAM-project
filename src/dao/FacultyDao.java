package by.epam.jvd.vitebsk.task4.dao;

import java.util.List;

import by.epam.jvd.vitebsk.task4.domain.Faculty;

public interface FacultyDao extends Dao<Faculty> {

    Faculty readByName(String name) throws DaoException;

    List<Faculty> readAll() throws DaoException;
}
