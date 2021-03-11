package by.epam.jvd.vitebsk.task4.service.user;

import by.epam.jvd.vitebsk.task4.service.ServiceException;

public class UserLoginNotUniqueException extends ServiceException {
    /**
     * 
     */
    private static final long serialVersionUID = -2244542575836916343L;
    private String login;

    public UserLoginNotUniqueException(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
}