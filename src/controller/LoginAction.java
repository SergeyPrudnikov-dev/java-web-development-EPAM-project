package by.epam.jvd.vitebsk.task4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class LoginAction extends Action {

    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("work");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null) {
            try {
                UserService userService = getServiceFactory().getUserService();
                User user = userService.findByLoginAndPassword(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("currentUser", user);
                    return new Forward("/index.html");
                } else {
                    logger.info("Incorrect password");
                    return new Forward("/login.html?message=login.message.incorrect.password");
                }
            } catch (ServiceFactoryException e) {
                logger.error(e);
                throw new ServletException(e);
            } catch (ServiceException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
