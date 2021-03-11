package by.epam.jvd.vitebsk.task4.controller.user;

import java.io.IOException;

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
import by.epam.jvd.vitebsk.task4.domain.Role;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class UserSaveAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        Role sessionUserRole;
        User sessionUser;

        user = new User();
        try {
            user.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
            logger.error(e);
        }
        user.setLogin(req.getParameter("login"));
        user.setEmail(req.getParameter("email"));
        try {
            user.setRole(Role.values()[Integer.parseInt(req.getParameter("role"))]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        }
        if (user.getLogin() != null && user.getRole() != null && user.getEmail() != null) {
            try {
                UserService userService = getServiceFactory().getUserServiceWithTransaction();
                userService.save(user);
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
            return new Forward("/user/list.html");
        } else {
            return new Forward("/entrant/list.html");
        }
    }
}