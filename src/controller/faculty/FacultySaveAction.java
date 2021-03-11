package by.epam.jvd.vitebsk.task4.controller.faculty;

import java.io.IOException;
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

public class FacultySaveAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Faculty faculty = new Faculty(); // dao-сущность, факультет
        try {
            faculty.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        faculty.setName(req.getParameter("name"));

        try {
            faculty.setRecruitment_plan(Integer.parseInt(req.getParameter("recruitment_plan")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        faculty.setCertificate(req.getParameter("certificate"));
        faculty.setSubject_1(req.getParameter("subject_1"));
        faculty.setSubject_2(req.getParameter("subject_2"));
        faculty.setSubject_3(req.getParameter("subject_3"));
        if (faculty.getName() != null) {
            try {
                FacultyService facultyService = getServiceFactory().getFacultyServiceWithTransaction();
                facultyService.save(faculty);
            } catch (ServiceFactoryException | ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        }
        return new Forward("/faculty/list.html");
    }
}