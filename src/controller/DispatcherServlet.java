package by.epam.jvd.vitebsk.task4.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.di.MainServiceFactoryImpl;
import by.epam.jvd.vitebsk.task4.di.ServiceFactory;

public class DispatcherServlet extends HttpServlet {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
                | ServletException | IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException
                | ServletException | IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public ServiceFactory getServiceFactory() {
        return new MainServiceFactoryImpl();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        String url = req.getRequestURI();
        String context = req.getContextPath();
        int postfixIndex = url.lastIndexOf(".html"); // если аргумент строки встречается один или несколько раз в
                                                     // подстроке в этом объекте, то он возвращает индекс первого
                                                     // символа последней такой подстроки.Если такого значения k не
                                                     // существует, возвращается -1.
        if (postfixIndex != -1) {
            url = url.substring(context.length(), postfixIndex); // Возвращает строку, которая является подстрокой этой
                                                                 // строки. Подстрока начинается с указанного beginIndex
                                                                 // и продолжается до символа с индексом endIndex - 1.
                                                                 // Таким образом, длина подстроки равна
                                                                 // endIndex-beginIndex.

        } else {
            url = url.substring(context.length());
        }
        Action action = ActionFactory.getAction(url); // Action getAction(String url) возвращает класс с конкретным
                                                      // действием
        Forward forward = null;
        if (action != null) {
            try (ServiceFactory factory = getServiceFactory()) {
                action.setServiceFactory(factory); // TODO ?
                forward = action.execute(req, resp);
            } catch (Exception e) {
                logger.error(e);
                //throw new ServletException(e);
            }
        }
        if (forward != null && forward.isRedirect()) {
            resp.sendRedirect(context + forward.getUrl());
        } else {
            if (forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }
}
