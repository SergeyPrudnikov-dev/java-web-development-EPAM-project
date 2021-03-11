package by.epam.jvd.vitebsk.task4.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.dao.DaoException;
import by.epam.jvd.vitebsk.task4.dao.StatementDao;
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;

public class StatementDaoImpl implements StatementDao {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private Connection connection;

    public StatementDaoImpl(Connection connection) {
    }

    public StatementDaoImpl() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(StatementByEntrant statementByEntrant) throws DaoException {
        String sql = "INSERT INTO `statements` (`faculty_id`, `last_name`, `first_name`, `patronymic`, `passport_id`, `certificate_score`, `subject_1_score`, "
                + "`subject_2_score`, `subject_3_score`, `date`, `user_id`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement prStatement = null;
        ResultSet resultSet = null;
        try {
            prStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prStatement.setLong(1, statementByEntrant.getFaculty().getId());
            prStatement.setString(2, statementByEntrant.getLastName());
            prStatement.setString(3, statementByEntrant.getFirstName());
            prStatement.setString(4, statementByEntrant.getPatronymic());
            prStatement.setString(5, statementByEntrant.getPassportId());
            prStatement.setInt(6, statementByEntrant.getCertificateScore());
            prStatement.setInt(7, statementByEntrant.getSubjectScore1());
            prStatement.setInt(8, statementByEntrant.getSubjectScore2());
            prStatement.setInt(9, statementByEntrant.getSubjectScore3());
            prStatement.setTimestamp(10, new Timestamp(statementByEntrant.getDate().getTime())); // TODO проверить
                                                                                                 // работу дата
            prStatement.setLong(11, statementByEntrant.getUserId());
            prStatement.executeUpdate();
            Long id = null;
            resultSet = prStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(prStatement);
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
    public StatementByEntrant read(Long id) throws DaoException {
        String sql = "SELECT `faculty_id`, `last_name`, `first_name`, `patronymic`, `passport_id`, `certificate_score`, `subject_1_score`, "
                + "`subject_2_score`, `subject_3_score`, `date`, `user_id` FROM `statements` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            StatementByEntrant statementByEntrant = null;
            if (resultSet.next()) {
                statementByEntrant = new StatementByEntrant();
                statementByEntrant.setId(id);

                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getLong("faculty_id"));
                statementByEntrant.setFaculty(faculty);
                statementByEntrant.setLastName(resultSet.getString("last_name"));
                statementByEntrant.setFirstName(resultSet.getString("first_name"));
                statementByEntrant.setPatronymic(resultSet.getString("patronymic"));
                statementByEntrant.setPassportId(resultSet.getString("passport_id"));
                statementByEntrant.setCertificateScore(resultSet.getInt("certificate_score"));
                statementByEntrant.setSubjectScore1(resultSet.getInt("subject_1_score"));
                statementByEntrant.setSubjectScore2(resultSet.getInt("subject_2_score"));
                statementByEntrant.setSubjectScore3(resultSet.getInt("subject_3_score"));
                statementByEntrant.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                statementByEntrant.setUserId(resultSet.getLong("user_id"));
            }
            return statementByEntrant;
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
    public void update(StatementByEntrant statementByEntrant) throws DaoException {
        String sql = "UPDATE `statements` SET `faculty_id` = ?, `last_name` = ?, `first_name` = ?, `patronymic` = ?, `passport_id` = ?, `certificate_score` = ?, `subject_1_score` = ?, "
                + "`subject_2_score` = ?, `subject_3_score` = ?, `date` = ? WHERE `id` = ?";
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(sql);
            prStatement.setLong(1, statementByEntrant.getFaculty().getId());
            prStatement.setString(2, statementByEntrant.getLastName());
            prStatement.setString(3, statementByEntrant.getFirstName());
            prStatement.setString(4, statementByEntrant.getPatronymic());
            prStatement.setString(5, statementByEntrant.getPassportId());
            prStatement.setInt(6, statementByEntrant.getCertificateScore());
            prStatement.setInt(7, statementByEntrant.getSubjectScore1());
            prStatement.setInt(8, statementByEntrant.getSubjectScore2());
            prStatement.setInt(9, statementByEntrant.getSubjectScore3());
            prStatement.setTimestamp(10, new Timestamp(statementByEntrant.getDate().getTime()));
            prStatement.setLong(11, statementByEntrant.getId());
            prStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(prStatement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    public void delete(Long id) throws DaoException {

        String sql = "DELETE FROM `statements` WHERE `id` = ?";
        PreparedStatement prStatement = null;
        try {
            prStatement = connection.prepareStatement(sql);
            prStatement.setLong(1, id);
            prStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(prStatement);
            } catch (SQLException e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    public List<StatementByEntrant> readAll() throws DaoException {
        String sql = "SELECT `id`, `faculty_id`, `last_name`, `first_name`, `patronymic`, `passport_id`, `certificate_score`, `subject_1_score`, "
                + "`subject_2_score`, `subject_3_score`, `date`, `user_id` FROM `statements`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql); // запрос на выборку
            List<StatementByEntrant> statementsByEntr;
            StatementByEntrant statementByEntrant;
            statementsByEntr = new ArrayList<>();
            while (resultSet.next()) {
                statementByEntrant = new StatementByEntrant();
                statementByEntrant.setId(resultSet.getLong("id"));

                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getLong("faculty_id")); // у объекта Faculty проинициа-но 1 поле, id
                statementByEntrant.setFaculty(faculty);

                statementByEntrant.setLastName(resultSet.getString("last_name"));
                statementByEntrant.setFirstName(resultSet.getString("first_name"));
                statementByEntrant.setPatronymic(resultSet.getString("patronymic"));
                statementByEntrant.setPassportId(resultSet.getString("passport_id"));
                statementByEntrant.setCertificateScore(resultSet.getInt("certificate_score"));
                statementByEntrant.setSubjectScore1(resultSet.getInt("subject_1_score"));
                statementByEntrant.setSubjectScore2(resultSet.getInt("subject_2_score"));
                statementByEntrant.setSubjectScore3(resultSet.getInt("subject_3_score"));
                statementByEntrant.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                statementByEntrant.setUserId(resultSet.getLong("user_id"));
                statementsByEntr.add(statementByEntrant);
            }
            return statementsByEntr;
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
    public List<StatementByEntrant> readByFaculty(Faculty faculty) throws DaoException {
        String sql = "SELECT `id`, `last_name`, `first_name`, `patronymic`, `passport_id`, `certificate_score`, `subject_1_score`, "
                + "`subject_2_score`, `subject_3_score`, `date`, `user_id` FROM `statements` WHERE `faculty_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, faculty.getId());
            resultSet = statement.executeQuery();
            List<StatementByEntrant> statementsByEntr;
            StatementByEntrant statementByEntrant;
            statementsByEntr = new ArrayList<>();
            while (resultSet.next()) {
                statementByEntrant = new StatementByEntrant();
                statementByEntrant.setId(resultSet.getLong("id"));
                Faculty currentFaculty = new Faculty();
                currentFaculty.setId(resultSet.getLong("faculty_id")); // у объекта Faculty проинициа-но 1 поле, id
                statementByEntrant.setFaculty(currentFaculty);
                statementByEntrant.setLastName(resultSet.getString("last_name"));
                statementByEntrant.setFirstName(resultSet.getString("first_name"));
                statementByEntrant.setPatronymic(resultSet.getString("patronymic"));
                statementByEntrant.setPassportId(resultSet.getString("passport_id"));
                statementByEntrant.setCertificateScore(resultSet.getInt("certificate_score"));
                statementByEntrant.setSubjectScore1(resultSet.getInt("subject_1_score"));
                statementByEntrant.setSubjectScore2(resultSet.getInt("subject_2_score"));
                statementByEntrant.setSubjectScore3(resultSet.getInt("subject_3_score"));
                statementByEntrant.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                statementByEntrant.setUserId(resultSet.getLong("user_id"));
                statementsByEntr.add(statementByEntrant);
            }
            return statementsByEntr;
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

    @Override
    public StatementByEntrant readBypassportId(String passportId) throws DaoException {
        String sql = "SELECT `id`, `faculty_id`, `last_name`, `first_name`, `patronymic`, `passport_id`, `certificate_score`, `subject_1_score`, "
                + "`subject_2_score`, `subject_3_score`, `date`, `user_id` FROM `statements` WHERE `passport_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, passportId);
            resultSet = statement.executeQuery();
            StatementByEntrant statementByEntrant = null;
            if (resultSet.next()) {
                statementByEntrant = new StatementByEntrant();
                statementByEntrant.setId(resultSet.getLong("id"));

                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getLong("faculty_id")); // у объекта Faculty проинициа-но 1 поле, id
                statementByEntrant.setFaculty(faculty);
                statementByEntrant.setLastName(resultSet.getString("last_name"));
                statementByEntrant.setFirstName(resultSet.getString("first_name"));
                statementByEntrant.setPatronymic(resultSet.getString("patronymic"));
                statementByEntrant.setPassportId(passportId);
                statementByEntrant.setCertificateScore(resultSet.getInt("certificate_score"));
                statementByEntrant.setSubjectScore1(resultSet.getInt("subject_1_score"));
                statementByEntrant.setSubjectScore2(resultSet.getInt("subject_2_score"));
                statementByEntrant.setSubjectScore3(resultSet.getInt("subject_3_score"));
                statementByEntrant.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                statementByEntrant.setUserId(resultSet.getLong("user_id"));
            }
            return statementByEntrant;
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
    public StatementByEntrant readByUserId(Long userId) throws DaoException {
        String sql = "SELECT `id`,`faculty_id`, `last_name`, `first_name`, `patronymic`, `passport_id`, `certificate_score`, `subject_1_score`, "
                + "`subject_2_score`, `subject_3_score`, `date` FROM `statements` WHERE `user_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            StatementByEntrant statementByEntrant = null;
            if (resultSet.next()) {
                statementByEntrant = new StatementByEntrant();
                statementByEntrant.setId(resultSet.getLong("id"));

                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getLong("faculty_id")); // у объекта Faculty проинициа-но 1 поле, id
                statementByEntrant.setFaculty(faculty);
                statementByEntrant.setLastName(resultSet.getString("last_name"));
                statementByEntrant.setFirstName(resultSet.getString("first_name"));
                statementByEntrant.setPatronymic(resultSet.getString("patronymic"));
                statementByEntrant.setPassportId(resultSet.getString("passport_id"));
                statementByEntrant.setCertificateScore(resultSet.getInt("certificate_score"));
                statementByEntrant.setSubjectScore1(resultSet.getInt("subject_1_score"));
                statementByEntrant.setSubjectScore2(resultSet.getInt("subject_2_score"));
                statementByEntrant.setSubjectScore3(resultSet.getInt("subject_3_score"));
                statementByEntrant.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
            }
            return statementByEntrant;
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
}
