package by.epam.jvd.vitebsk.task4.controller.entrant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class EntrantListAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id, userId;
        id = null;
        userId = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        req.setAttribute("id", userId);

        if (id != null) {// если в req есть id заявления(statement) пользователя, то отправляем statement
                         // c данным id в req
            try {
                StatementService statementService = getServiceFactory().getStatementServiceWithTransaction();
                StatementByEntrant statementByEntrant = statementService.findById(id);
                if (statementByEntrant == null) {// если в базе данных нет заявки с таким id - создаем новую
                    statementByEntrant = new StatementByEntrant();
                }
                req.setAttribute("statementByEntrant", statementByEntrant);
            } catch (ServiceFactoryException | ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        } else { // иначе ищем statement по id текущего user и отправляем его в req
            try {
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("currentUser");
                userId = user.getId();
            } catch (NumberFormatException e) {
                logger.error(e);
            }
        }
        if (userId != null) {
            try {
                StatementService statementService = getServiceFactory().getStatementServiceWithTransaction();
                StatementByEntrant statementByEntrant = statementService.findByUserId(userId);
                if (statementByEntrant == null) {// если в базе данных нет заявдения с таким userId - создаем новую
                    statementByEntrant = new StatementByEntrant();
                    statementByEntrant.setUserId(userId);
                }
                req.setAttribute("statementByEntrant", statementByEntrant);
            } catch (ServiceFactoryException | ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        }
        try {
            FacultyService facultyService = getServiceFactory().getFacultyServiceWithTransaction();
            List<Faculty> faculties;
            faculties = new ArrayList<>();
            faculties = facultyService.findAll();
            req.setAttribute("faculties", faculties);
        } catch (ServiceFactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}