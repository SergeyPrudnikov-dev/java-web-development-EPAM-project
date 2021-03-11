package by.epam.jvd.vitebsk.task4.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.pool.ConnectionPool;
import by.epam.jvd.vitebsk.task4.pool.ConnectionPoolException;

public class ApplicationInitializerListener implements ServletContextListener { // работает автоматически
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) { // создает ConnectionPool при старте приложения
        logger.info("work");
        try {
            ConnectionPool.getInstance().init("com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/reception_commission_db?useUnicode=true&serverTimezone=", "root",
                    "root", 5, 100, 0);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            e.printStackTrace(); // TODO сюда нужно поставить логгер, для того, что бы фиксировать ошибки
                                 // связанные с инициализацией ConnectionPool
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { // уничтожает ConnectionPool при остановке или перезапуске
                                                            // приложения
        logger.info("work");
        ConnectionPool.getInstance().destroy();
    }

}
