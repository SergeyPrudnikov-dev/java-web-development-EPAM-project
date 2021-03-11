package by.epam.jvd.vitebsk.task4.controller.statement;

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
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class StatementListAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StatementService statementService = getServiceFactory().getStatementService();
            List<StatementByEntrant> statements = statementService.findAll();

            FacultyService facultyService = getServiceFactory().getFacultyService();

            long facultyId;
            Faculty faculty;
            for (StatementByEntrant statement : statements) {
                facultyId = statement.getFaculty().getId();
                faculty = facultyService.findById(facultyId);
                // устанавливаем имя факультета в каждое заявление, для отображения в JSP
                statement.getFaculty().setName(faculty.getName());
                // устанавливаем план набора в каждое заявление, для отображения в JSP
            }
            statementService.sortByFacultyName(statements);
            req.setAttribute("statements", statements);
        } catch (ServiceFactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (Exception e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
