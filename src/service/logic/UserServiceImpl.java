package by.epam.jvd.vitebsk.task4.service.logic;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.dao.DaoException;
import by.epam.jvd.vitebsk.task4.dao.UserDao;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserLoginNotUniqueException;
import by.epam.jvd.vitebsk.task4.service.user.UserNotExistsException;
import by.epam.jvd.vitebsk.task4.service.user.UserPasswordIncorrectException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class UserServiceImpl extends BaseService implements UserService {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private UserDao userDao;
    private String defaultPassword = "12345";

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.readAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User findById(Long id) throws ServiceException {
        try {
            return userDao.read(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(User user) throws ServiceException {
        try {
            getTransaction().start();
            if (user.getId() != null) {
                User storedUser = userDao.read(user.getId());
                if (storedUser != null) {
                    user.setPassword(storedUser.getPassword());
                    if (storedUser.getLogin().equals(user.getLogin()) || userDao.readByLogin(user.getLogin()) == null) {
                        userDao.update(user);
                    } else {
                        throw new UserLoginNotUniqueException(user.getLogin());
                    }
                } else {
                    throw new UserNotExistsException(user.getId());
                }
            } else {
                user.setPassword(defaultPassword);
                if (userDao.readByLogin(user.getLogin()) == null) {
                    Long id = userDao.create(user);
                    user.setId(id);
                } else {
                    throw new UserLoginNotUniqueException(user.getLogin());
                }
            }
            getTransaction().commit();
        } catch (DaoException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw e;
        }
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.readByLoginAndPassword(login, password);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) throws ServiceException {
        try {
            getTransaction().start();
            User user = userDao.read(userId);
            if (user != null) {
                if (user.getPassword().equals(oldPassword)) {
                    if (newPassword == null) {
                        newPassword = defaultPassword;
                    }
                    user.setPassword(newPassword);
                    userDao.update(user);
                } else {
                    throw new UserPasswordIncorrectException(user.getId());
                }
            } else {
                throw new UserNotExistsException(userId);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
