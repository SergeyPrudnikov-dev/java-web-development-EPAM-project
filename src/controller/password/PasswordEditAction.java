package by.epam.jvd.vitebsk.task4.controller.password;

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
import by.epam.jvd.vitebsk.task4.domain.User;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.user.UserService;

public class PasswordEditAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserService userService = getServiceFactory().getUserService();
            User user = userService.findById(Long.parseLong(req.getParameter("id")));
            if (user != null) {
                userService.changePassword(user.getId(), user.getPassword(), null);
            }
        } catch (ServiceFactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return new Forward("/user/list.html");
    }
}