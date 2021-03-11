package by.epam.jvd.vitebsk.task4.controller.statement;

import java.io.IOException;
import java.util.Date;

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
import by.epam.jvd.vitebsk.task4.domain.Role;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class StatementSaveAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Role sessionUserRole;
        User sessionUser;
        StatementByEntrant statementByEntrant; // dao-сущность, заявление абитуриента

        statementByEntrant = new StatementByEntrant();
        try {
            statementByEntrant.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        try {
            statementByEntrant.setUserId(Long.parseLong(req.getParameter("userId")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        try {
            Faculty faculty = new Faculty();
            faculty.setId(Long.parseLong(req.getParameter("faculty"))); // TODO проинициализирован только id
            statementByEntrant.setFaculty(faculty);
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        statementByEntrant.setLastName(req.getParameter("last_name"));
        statementByEntrant.setFirstName(req.getParameter("first_name"));
        statementByEntrant.setPatronymic(req.getParameter("patronymic"));
        statementByEntrant.setPassportId(req.getParameter("passport_id"));
        try {
            statementByEntrant.setCertificateScore(Integer.parseInt(req.getParameter("certificate_score")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        try {
            statementByEntrant.setSubjectScore1(Integer.parseInt(req.getParameter("subject_1_score")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        try {
            statementByEntrant.setSubjectScore2(Integer.parseInt(req.getParameter("subject_2_score")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        try {
            statementByEntrant.setSubjectScore3(Integer.parseInt(req.getParameter("subject_3_score")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        statementByEntrant.setDate(new Date());
        // проверяем, что бы все параметры были != null(условие в базе данных)
        if (statementByEntrant.getFaculty() != null && !statementByEntrant.getFirstName().isBlank()
                && !statementByEntrant.getLastName().isBlank() && !statementByEntrant.getPatronymic().isBlank()
                && !statementByEntrant.getPassportId().isBlank() && statementByEntrant.getCertificateScore() > 0
                && statementByEntrant.getCertificateScore() < 101 && statementByEntrant.getSubjectScore1() > 0
                && statementByEntrant.getSubjectScore1() < 101 && statementByEntrant.getSubjectScore2() > 0
                && statementByEntrant.getSubjectScore2() < 101 && statementByEntrant.getSubjectScore3() > 0
                && statementByEntrant.getSubjectScore3() < 101) {
            try {
                StatementService statementService = getServiceFactory().getStatementServiceWithTransaction();
                statementService.save(statementByEntrant);
            } catch (ServiceFactoryException | ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        }

        sessionUserRole = null;
        try {
            HttpSession session = req.getSession();
            sessionUser = (User) session.getAttribute("currentUser");
            sessionUserRole = sessionUser.getRole();
        } catch (NumberFormatException e) {
            logger.error(e);
        }

        if (sessionUserRole == Role.ADMIN) {
            return new Forward("/statement/list.html");
        } else {
            return new Forward("/entrant/list.html");
        }
    }
}