package by.epam.jvd.vitebsk.task4.controller.faculty;

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
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;

public class FacultyListAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FacultyService facultyService = getServiceFactory().getFacultyService();
            List<Faculty> faculties = facultyService.findAll();
            req.setAttribute("faculties", faculties);
            return null;
        } catch (ServiceFactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
    }
}
