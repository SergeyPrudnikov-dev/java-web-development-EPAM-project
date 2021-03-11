package by.epam.jvd.vitebsk.task4.di;

import by.epam.jvd.vitebsk.task4.service.Transaction;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledPersonService;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public interface ServiceFactory extends AutoCloseable {

    UserService getUserService() throws ServiceFactoryException;

    UserService getUserServiceWithTransaction() throws ServiceFactoryException;

    StatementService getStatementService() throws ServiceFactoryException;

    StatementService getStatementServiceWithTransaction() throws ServiceFactoryException;

    FacultyService getFacultyService() throws ServiceFactoryException;

    FacultyService getFacultyServiceWithTransaction() throws ServiceFactoryException;

    EnrolledPersonService getEnrolledPersonService() throws ServiceFactoryException;

    EnrolledPersonService getEnrolledPersonServiceWithTransaction() throws ServiceFactoryException;

    Transaction getTransaction() throws ServiceFactoryException;

}
