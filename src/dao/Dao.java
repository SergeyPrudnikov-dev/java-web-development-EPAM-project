package by.epam.jvd.vitebsk.task4.dao;

import java.sql.SQLException;
import java.sql.Statement;

import by.epam.jvd.vitebsk.task4.domain.Entity;

public interface Dao<T extends Entity> { // единый для любых(всех наших) сущностей

    Long create(T entity) throws DaoException;

    T read(Long id) throws DaoException; // по индификатору возвращает конкретную сущность

    void update(T entity) throws DaoException;

    void delete(Long id) throws DaoException;

    public void closeStatement(Statement statement) throws SQLException;

}
