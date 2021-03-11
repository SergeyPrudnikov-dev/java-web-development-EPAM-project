package by.epam.jvd.vitebsk.task4.controller.enrolledperson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledPersonService;

public class EnrolledPersonResetAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EnrolledPersonService enrolledService;
        List<EnrolledPerson> enrolledPersons;
        try {
            enrolledService = getServiceFactory().getEnrolledPersonServiceWithTransaction();
            enrolledPersons = enrolledService.findAll();
            enrolledService.deleteAll(enrolledPersons);
        } catch (ServiceFactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forward("/enrolledentrants/list.html");
    }
}
