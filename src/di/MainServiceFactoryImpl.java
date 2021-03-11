package by.epam.jvd.vitebsk.task4.di; //TODO dependency injection (внедрение зависимостей)

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.dao.EnrolledPersonDao;
import by.epam.jvd.vitebsk.task4.dao.FacultyDao;
import by.epam.jvd.vitebsk.task4.dao.StatementDao;
import by.epam.jvd.vitebsk.task4.dao.UserDao;
import by.epam.jvd.vitebsk.task4.dao.mysql.EnrolledPersonDaoImpl;
import by.epam.jvd.vitebsk.task4.dao.mysql.FacultyDaoImpl;
import by.epam.jvd.vitebsk.task4.dao.mysql.StatementDaoImpl;
import by.epam.jvd.vitebsk.task4.dao.mysql.UserDaoImpl;
import by.epam.jvd.vitebsk.task4.pool.ConnectionPool;
import by.epam.jvd.vitebsk.task4.pool.ConnectionPoolException;
import by.epam.jvd.vitebsk.task4.service.Transaction;
import by.epam.jvd.vitebsk.task4.service.TransactionException;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledPersonService;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;
import by.epam.jvd.vitebsk.task4.service.logic.EnrolledPersonServiceImpl;
import by.epam.jvd.vitebsk.task4.service.logic.FacultyServiceImpl;
import by.epam.jvd.vitebsk.task4.service.logic.StatementServiceImpl;
import by.epam.jvd.vitebsk.task4.service.logic.TransactionImpl;
import by.epam.jvd.vitebsk.task4.service.logic.UserServiceImpl;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class MainServiceFactoryImpl implements ServiceFactory {

    // TODO здесь реализованы однотипные методы для всех сущностей,
    // не успел изменить на шаблон проектирования
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private UserService userService = null;
    private UserDao userDao = null;

    private StatementService statementService = null;
    private StatementDao statementDao = null;

    private FacultyService facultyService = null;
    private FacultyDao facultyDao = null;

    private EnrolledPersonService enrPersonService = null;
    private EnrolledPersonDao enrPersonDao = null;

    private Connection connection = null;
    TransactionImpl transaction = null;

    @Override
    public UserService getUserService() throws ServiceFactoryException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userService = userServiceImpl;
        }
        return userService;
    }

    @Override
    public UserService getUserServiceWithTransaction() throws ServiceFactoryException {
        if (userService == null) {
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setTransaction(getTransaction());
            try {
                transaction.start(); // TODO транзакция закрывается в методах сервиса( ...ServiceImpl)
            } catch (TransactionException e) {
                logger.error(e);
                throw new ServiceFactoryException(e);
            }
            userServiceImpl.setUserDao(getUserDao());
            userService = userServiceImpl;
        }
        return userService;
    }

    private UserDao getUserDao() throws ServiceFactoryException {
        if (userDao == null) { // TODO для того что бы на один поток(для одного пользователя/запроса)
            UserDaoImpl userDaoImpl = new UserDaoImpl(); // создавался 1 MainServiceFactoryImpl
            userDaoImpl.setConnection(getConnectionFromFactory());
            userDao = userDaoImpl;
        }
        return userDao;
    }

    private StatementDao getStatementDao() throws ServiceFactoryException {
        if (statementDao == null) { // TODO для того что бы на один поток(для одного пользователя/запроса)
            StatementDaoImpl statementDaoImpl = new StatementDaoImpl(); // создавался 1 MainServiceFactoryImpl
            statementDaoImpl.setConnection(getConnectionFromFactory());
            statementDao = statementDaoImpl;
        }
        return statementDao;
    }

    @Override
    public StatementService getStatementService() throws ServiceFactoryException {
        if (statementService == null) {
            StatementServiceImpl statementServiceImpl = new StatementServiceImpl();
            statementServiceImpl.setStatementDao(getStatementDao());
            statementService = statementServiceImpl;
        }
        return statementService;
    }

    @Override
    public StatementService getStatementServiceWithTransaction() throws ServiceFactoryException {
        if (statementService == null) {
            StatementServiceImpl statementServiceImpl = new StatementServiceImpl();
            statementServiceImpl.setTransaction(getTransaction());
            try {
                transaction.start(); // TODO транзакция закрывается в методах сервиса( ...ServiceImpl)
            } catch (TransactionException e) {
                logger.error(e);
                throw new ServiceFactoryException(e);
            }
            statementServiceImpl.setStatementDao(getStatementDao());
            statementService = statementServiceImpl;
        }
        return statementService;
    }

    @Override
    public FacultyService getFacultyService() throws ServiceFactoryException {
        if (facultyService == null) {
            FacultyServiceImpl facultyServiceImpl = new FacultyServiceImpl();
            facultyServiceImpl.setFacultyDao(getFacultyDao());
            facultyService = facultyServiceImpl;
        }
        return facultyService;
    }

    @Override
    public FacultyService getFacultyServiceWithTransaction() throws ServiceFactoryException {
        if (facultyService == null) {
            FacultyServiceImpl facultyServiceImpl = new FacultyServiceImpl();
            facultyServiceImpl.setTransaction(getTransaction());
            try {
                transaction.start(); // TODO транзакция закрывается в методах сервиса( ...ServiceImpl)
            } catch (TransactionException e) {
                logger.error(e);
                throw new ServiceFactoryException(e);
            }
            facultyServiceImpl.setFacultyDao(getFacultyDao());
            facultyService = facultyServiceImpl;
        }
        return facultyService;
    }

    private FacultyDao getFacultyDao() throws ServiceFactoryException {
        if (facultyDao == null) { // TODO для того что бы на один поток(для одного пользователя/запроса)
            FacultyDaoImpl facultyDaoImpl = new FacultyDaoImpl(); // создавался 1 MainServiceFactoryImpl
            facultyDaoImpl.setConnection(getConnectionFromFactory());
            facultyDao = facultyDaoImpl;
        }
        return facultyDao;
    }

    @Override
    public EnrolledPersonService getEnrolledPersonService() throws ServiceFactoryException {
        if (enrPersonService == null) {
            EnrolledPersonServiceImpl enrPersonServiceImpl = new EnrolledPersonServiceImpl();
            enrPersonServiceImpl.setEnrolledPersonDao(getEnrolledPersonDao());
            enrPersonService = enrPersonServiceImpl;
        }
        return enrPersonService;
    }

    @Override
    public EnrolledPersonService getEnrolledPersonServiceWithTransaction() throws ServiceFactoryException {
        EnrolledPersonServiceImpl enrPersonServiceImpl = new EnrolledPersonServiceImpl();
        enrPersonServiceImpl.setTransaction(getTransaction());
        try {
            transaction.start(); // TODO транзакция закрывается в методах сервиса( ...ServiceImpl)
        } catch (TransactionException e) {
            logger.error(e);
            throw new ServiceFactoryException(e);
        }
        enrPersonServiceImpl.setEnrolledPersonDao(getEnrolledPersonDao());
        enrPersonService = enrPersonServiceImpl;
        return enrPersonService;
    }

    private EnrolledPersonDao getEnrolledPersonDao() throws ServiceFactoryException {
        if (enrPersonDao == null) { // TODO для того что бы на один поток(для одного пользователя/запроса)
            EnrolledPersonDaoImpl enrPersonDaoImpl = new EnrolledPersonDaoImpl(); // создавался 1 MainServiceFactoryImpl
            enrPersonDaoImpl.setConnection(getConnectionFromFactory());
            enrPersonDao = enrPersonDaoImpl;
        }
        return enrPersonDao;
    }

    private Connection getConnectionFromFactory() throws ServiceFactoryException {
        if (connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (ConnectionPoolException e) {
                logger.error(e);
                throw new ServiceFactoryException(e);
            }
        }
        return connection;
    }

    @Override
    public Transaction getTransaction() throws ServiceFactoryException {
        if (transaction == null) {
            transaction = new TransactionImpl();
            transaction.setConnection(getConnectionFromFactory());
        }
        return transaction;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
