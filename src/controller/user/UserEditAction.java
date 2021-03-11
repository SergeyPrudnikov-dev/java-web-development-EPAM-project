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

public class UserEditAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user;
        User sessionUser;
        Long id;
        user = null;
        sessionUser = null;
        id = null;

        try {
            id = Long.parseLong(req.getParameter("id")); // поиск id (случай перехода через список всех пользователей)
        } catch (NumberFormatException e) {
            logger.error(e);
        }

        try {
            HttpSession session = req.getSession();
            sessionUser = (User) session.getAttribute("currentUser");
        } catch (NumberFormatException e) {
            logger.error(e);
        }

        if (id != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                user = userService.findById(id);
                req.setAttribute("user", user);
            } catch (ServiceFactoryException | ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        }
        
        if (sessionUser.getRole() == Role.ADMIN) {
            req.setAttribute("roles", Role.values());
            req.setAttribute("sessionUser", sessionUser);
        } else {
            Role[] roles = { sessionUser.getRole() };
            req.setAttribute("roles", roles);
            req.setAttribute("user", sessionUser);
        }
        return null;
    }
}
