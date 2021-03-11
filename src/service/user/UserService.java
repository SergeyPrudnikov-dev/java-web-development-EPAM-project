package by.epam.jvd.vitebsk.task4.service.user;

import java.util.List;

import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;

public interface UserService {
    List<User> findAll() throws ServiceException;

    User findById(Long id) throws ServiceException;

    void save(User user) throws ServiceException;

    void delete(Long id) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException;
}
