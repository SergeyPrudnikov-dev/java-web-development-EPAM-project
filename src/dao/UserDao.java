package by.epam.jvd.vitebsk.task4.dao;

import java.util.List;

import by.epam.jvd.vitebsk.task4.domain.User;

public interface UserDao extends Dao<User> {

    List<User> readAll() throws DaoException;

    User readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

}
