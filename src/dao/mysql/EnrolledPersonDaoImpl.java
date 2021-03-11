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
import by.epam.jvd.vitebsk.task4.dao.EnrolledPersonDao;
import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;

public class EnrolledPersonDaoImpl implements EnrolledPersonDao {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    private Connection connection;

    public EnrolledPersonDaoImpl(Connection connection) {
    }

    public EnrolledPersonDaoImpl() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(EnrolledPerson enrolledPerson) throws DaoException {
        String sql = "INSERT INTO `list_enrolled_persons` (`full_name`, `total_score`, `faculty_id`, `statement_id`) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, enrolledPerson.getFullName());
            statement.setInt(2, enrolledPerson.getTotalScore());
            statement.setLong(3, enrolledPerson.getFacultyId());
            statement.setLong(4, enrolledPerson.getStatementId());
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
    public EnrolledPerson read(Long id) throws DaoException {
        String sql = "SELECT `full_name`, `total_score`,`faculty_id`, `statement_id` FROM `list_enrolled_persons` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            EnrolledPerson enrolledPerson = null;
            if (resultSet.next()) {
                enrolledPerson = new EnrolledPerson();
                enrolledPerson.setId(id);
                enrolledPerson.setFullName(resultSet.getString("full_name"));
                enrolledPerson.setTotalScore(resultSet.getInt("total_score"));
                enrolledPerson.setFacultyId(resultSet.getLong("faculty_id"));
                enrolledPerson.setStatementId(resultSet.getLong("statement_id"));
            }
            return enrolledPerson;
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
            } catch (Exception e) { // подразумеваем (SQLException, NullPointerException)
                logger.error(e);
            }
        }
    }

    @Override
    public List<EnrolledPerson> readByFullName(String fullName) throws DaoException {
        String sql = "SELECT `id`, `total_score`,`faculty_id`, `statement_id` FROM `list_enrolled_persons` WHERE `full_name` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, fullName);
            resultSet = statement.executeQuery();
            List<EnrolledPerson> enrolledPersons = new ArrayList<>();
            while (resultSet.next()) {
                EnrolledPerson enrolledPerson = new EnrolledPerson();
                enrolledPerson.setId(resultSet.getLong("id"));
                enrolledPerson.setFullName(fullName);
                enrolledPerson.setTotalScore(resultSet.getInt("total_score"));
                enrolledPerson.setFacultyId(resultSet.getLong("faculty_id"));
                enrolledPerson.setStatementId(resultSet.getLong("statement_id"));
                enrolledPersons.add(enrolledPerson);
            }
            return enrolledPersons;
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
    public List<EnrolledPerson> readAll() throws DaoException { // здесь описан типовой метод, в проекте мы его
                                                                // копипастим, в больших проектах его автоматизируют
        String sql = "SELECT `id`, `full_name`, `total_score`,`faculty_id`, `statement_id` FROM `list_enrolled_persons`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); // запрос на выборку
            List<EnrolledPerson> enrolledPersons = new ArrayList<>();
            while (resultSet.next()) { // 4. Process java.sql.ResultSet
                EnrolledPerson enrolledPerson = new EnrolledPerson();
                enrolledPerson.setId(resultSet.getLong("id"));
                enrolledPerson.setFullName(resultSet.getString("full_name"));
                enrolledPerson.setTotalScore(resultSet.getInt("total_score"));
                enrolledPerson.setFacultyId(resultSet.getLong("faculty_id"));
                enrolledPerson.setStatementId(resultSet.getLong("statement_id"));
                enrolledPersons.add(enrolledPerson);
            }
            return enrolledPersons;
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
    public void update(EnrolledPerson enrolledPerson) throws DaoException {
        String sql = "UPDATE `list_enrolled_persons` SET `full_name` = ?, `total_score` = ?, `faculty_id` = ?, `statement_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, enrolledPerson.getFullName());
            statement.setInt(2, enrolledPerson.getTotalScore());
            statement.setLong(3, enrolledPerson.getFacultyId());
            statement.setLong(4, enrolledPerson.getStatementId());
            statement.setLong(5, enrolledPerson.getId());
            statement.executeUpdate();
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
        String sql = "DELETE FROM `list_enrolled_persons` WHERE `id` = ?";
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
