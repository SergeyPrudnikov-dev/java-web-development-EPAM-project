package by.epam.jvd.vitebsk.task4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.dao.DaoException;
import by.epam.jvd.vitebsk.task4.dao.UserDao;

import by.epam.jvd.vitebsk.task4.domain.Role;
import by.epam.jvd.vitebsk.task4.domain.User;

public class UserDaoImpl implements UserDao { // реализация интерфейса UserDao
    // объект подключения к базе данных, здесь мы получаем его готовым через
    // setConnection
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private Connection connection;

    public UserDaoImpl(Connection connection) {
    }

    public UserDaoImpl() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(User user) throws DaoException {
        String sql = "INSERT INTO `users` (`login`, `password`, `email`, `role_id`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getRole().ordinal());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `login`, `password`,`email`, `role_id` FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role_id")]);
            }
            return user;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    @Override
    public User readByLogin(String login) throws DaoException {
        String sql = "SELECT `id`, `password`,`email`, `role_id` FROM `users` WHERE `login` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role_id")]);
            }
            return user;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    @Override
    public User readByLoginAndPassword(String login, String password) throws DaoException {
        String sql = "SELECT `id`, `email`, `role_id` FROM `users` WHERE `login` = ? AND `password` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role_id")]);
            }
            return user;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    @Override
    public List<User> readAll() throws DaoException {
        String sql = "SELECT `id`, `login`, `password`,`email`, `role_id` FROM `users`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(Role.values()[resultSet.getInt("role_id")]);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
            try {
                resultSet.close();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `users` SET `login` = ?, `password` = ?, `email` = ?, `role_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getRole().ordinal());
            statement.setLong(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void closeStatement(Statement statement) throws SQLException {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SQLException(e);
        }
    }
}
