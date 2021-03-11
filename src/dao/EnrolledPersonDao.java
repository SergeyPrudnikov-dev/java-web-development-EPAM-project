package by.epam.jvd.vitebsk.task4.dao;

import java.util.List;

import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;

public interface EnrolledPersonDao extends Dao<EnrolledPerson> { // интерфейс конкретно под определенную
                                                                 // сущность(пользователя)

    List<EnrolledPerson> readAll() throws DaoException;

    List<EnrolledPerson> readByFullName(String fullName) throws DaoException; // Реализуем поиск с учетом возможного
                                                                              // полного совпадения ФИО
}
