package by.epam.jvd.vitebsk.task4.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.enrolledperson.EnrolledPersonCreateAction;
import by.epam.jvd.vitebsk.task4.controller.enrolledperson.EnrolledPersonListAction;
import by.epam.jvd.vitebsk.task4.controller.enrolledperson.EnrolledPersonResetAction;
import by.epam.jvd.vitebsk.task4.controller.faculty.FacultyDeleteAction;
import by.epam.jvd.vitebsk.task4.controller.faculty.FacultyEditAction;
import by.epam.jvd.vitebsk.task4.controller.faculty.FacultyListAction;
import by.epam.jvd.vitebsk.task4.controller.faculty.FacultySaveAction;
import by.epam.jvd.vitebsk.task4.controller.password.PasswordEditAction;
import by.epam.jvd.vitebsk.task4.controller.password.PasswordSaveAction;
import by.epam.jvd.vitebsk.task4.controller.statement.StatementDeleteAction;
import by.epam.jvd.vitebsk.task4.controller.statement.StatementEditAction;
import by.epam.jvd.vitebsk.task4.controller.statement.StatementListAction;
import by.epam.jvd.vitebsk.task4.controller.statement.StatementSaveAction;
import by.epam.jvd.vitebsk.task4.controller.user.UserDeleteAction;
import by.epam.jvd.vitebsk.task4.controller.user.UserEditAction;
import by.epam.jvd.vitebsk.task4.controller.user.UserListAction;
import by.epam.jvd.vitebsk.task4.controller.user.UserSaveAction;

public class ActionFactory {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private static Map<String, Class<? extends Action>> actions = new HashMap<>();
    static {
        actions.put("/", MainAction.class);
        actions.put("/index", MainAction.class);
        actions.put("/login", LoginAction.class);
        actions.put("/logout", LogoutAction.class);
        actions.put("/password/save", PasswordSaveAction.class);
        actions.put("/password/reset", PasswordEditAction.class);
        actions.put("/user/list", UserListAction.class);
        actions.put("/user/edit", UserEditAction.class);
        actions.put("/user/save", UserSaveAction.class);
        actions.put("/user/delete", UserDeleteAction.class);
        actions.put("/statement/list", StatementListAction.class);
        actions.put("/statement/save", StatementSaveAction.class);
        actions.put("/statement/edit", StatementEditAction.class);
        actions.put("/statement/delete", StatementDeleteAction.class);
        actions.put("/faculty/list", FacultyListAction.class);
        actions.put("/faculty/edit", FacultyEditAction.class);
        actions.put("/faculty/save", FacultySaveAction.class);
        actions.put("/faculty/delete", FacultyDeleteAction.class);
        actions.put("/enrolledentrants/list", EnrolledPersonListAction.class);
        actions.put("/enrolledentrants/create", EnrolledPersonCreateAction.class);
        actions.put("/enrolledentrants/reset", EnrolledPersonResetAction.class);
        actions.put("/logout", LogoutAction.class);
    }

    public static Action getAction(String url) throws ServletException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        Class<?> action = actions.get(url); // TODO разобраться объект класса Class
        if (action != null) {
            try {
                return (Action) action.getDeclaredConstructor().newInstance(); // TODO разобраться с newInstance(),
                                                                               // action.getConstructor().newInstance()
            } catch (InstantiationException | IllegalAccessException | NullPointerException e) {
                logger.error(e);
                throw new ServletException(e);
            }
        } else {
            return null;
        }
    }
}
