package by.epam.jvd.vitebsk.task4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("work");
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new Forward("/index.html");
    }
}
