package by.epam.jvd.vitebsk.task4.pool;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;

public class ConnectionPool {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private String jdbcUrl;
    private String user;
    private String password;
    private int maxSize;
    private int validationConnectionTimeout;

    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>(
            (c1, c2) -> Integer.compare(c1.hashCode(), c2.hashCode())); // через лямбда-вырожение реализовали компаратор
                                                                        // для ConcurrentSkipListSet

    private ConnectionPool() {
    }

    // init - инициализирует pool и создает некоторое ко-во соединений (minSize),
    // вызываем при старте приложения
    public void init(String driverClass, String jdbcUrl, String user, String password, int minSize, int maxSize,
            int validationConnectionTimeout) throws ConnectionPoolException {
        logger.info("work");
        try {
            destroy(); // на случай если уже была инициализация, закрываем все соединения
            Driver driver = (Driver) Class.forName(driverClass).getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            this.jdbcUrl = jdbcUrl;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.validationConnectionTimeout = validationConnectionTimeout;
            for (int i = 0; i < minSize; i++) {
                freeConnections.add(newConnection());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException e) {
            logger.error(e);
            throw new ConnectionPoolException(e);
        }
    }

    public Connection getConnection() throws ConnectionPoolException { // забирает соединение из пула
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnections.poll();
                if (connection != null) {
                    if (!connection.isValid(validationConnectionTimeout)) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                        } // TODO здесь придушили Exception
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = newConnection();
                } else {
                    throw new ConnectionPoolException("The database connections number exceede limit.");
                }
            } catch (SQLException e) {
                logger.error(e);
                throw new ConnectionPoolException(e);
            }
        }
        usedConnections.add(connection);
        return new ConnectionWrapper(connection); // возвращаем обернутый connection;
    }

    void freeConnection(Connection connection) { // атрибут доступа по умолчанию (pakage), что бы метод видили только в
                                                 // данном пакете
        // возвращает соединение в пул(освобождает), здесь мы не проверяем connection на
        // валидность,
        // после возвращения в пул и использования в getConnection(),
        // в getConnection() он проверится на валидность
        try {
            connection.clearWarnings();
            usedConnections.remove(connection);
            freeConnections.add(connection);
        } catch (SQLException e) {
            logger.error(e);
            try {
                connection.close();
            } catch (SQLException e1) {
                logger.error(e);
            }
        }
    }

    public void destroy() { // вызываем, при закрытии приложения
        logger.info("work");
        synchronized (freeConnections) {
            synchronized (usedConnections) {
                usedConnections.addAll(freeConnections);
                freeConnections.clear();
                for (Connection connection : usedConnections) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        logger.error(e);
                    }
                }
                usedConnections.clear();
            }
        }
    }

    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

}
