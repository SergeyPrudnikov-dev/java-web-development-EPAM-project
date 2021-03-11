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
import by.epam.jvd.vitebsk.task4.dao.FacultyDao;
import by.epam.jvd.vitebsk.task4.domain.Faculty;

public class FacultyDaoImpl implements FacultyDao {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private Connection connection; // объект подключения к базе данных, здесь мы получаем его готовым через
                                   // setConnection

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(Faculty faculty) throws DaoException {
        String sql = "INSERT INTO `faculties` (`name`, `recruitment_plan`,`certificate`, `subject_1`, `subject_2`, `subject_3`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getRecruitment_plan());
            statement.setString(3, faculty.getCertificate());
            statement.setString(4, faculty.getSubject_1());
            statement.setString(5, faculty.getSubject_2());
            statement.setString(6, faculty.getSubject_3());
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
            } catch (Exception e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    @Override
    public Faculty read(Long id) throws DaoException {
        String sql = "SELECT `name`, `recruitment_plan`,`certificate`, `subject_1`, `subject_2`, `subject_3` FROM `faculties` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Faculty faculty = null;
            if (resultSet.next()) {
                faculty = new Faculty();
                faculty.setId(id);
                faculty.setName(resultSet.getString("name"));
                faculty.setRecruitment_plan(resultSet.getInt("recruitment_plan"));
                faculty.setCertificate(resultSet.getString("certificate"));
                faculty.setSubject_1(resultSet.getString("subject_1"));
                faculty.setSubject_2(resultSet.getString("subject_2"));
                faculty.setSubject_3(resultSet.getString("subject_3"));
            }
            return faculty;
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {
                logger.error(e);
            }
            try {
                closeStatement(statement);
            } catch (Exception e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void update(Faculty faculty) throws DaoException {
        String sql = "UPDATE `faculties` SET `name` = ?, `recruitment_plan` = ?, `certificate` = ?, `subject_1` = ?, `subject_2` = ?, `subject_3` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, faculty.getName());
            statement.setInt(2, faculty.getRecruitment_plan());
            statement.setString(3, faculty.getCertificate());
            statement.setString(4, faculty.getSubject_1());
            statement.setString(5, faculty.getSubject_2());
            statement.setString(6, faculty.getSubject_3());
            statement.setLong(7, faculty.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        } finally {
            try {
                closeStatement(statement);
            } catch (Exception e) {
                logger.error(e);
                throw new DaoException(e);
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        // TODO не успел переделать иерархию с общим параметризованным класом
        // BaseDaoImpl у
        // которого будет общее поле
        // Connection с геттером и сеттером, от которого буду наследоваться все
        // реализации. Тогда в нем можно и
        // реализовать для всех наследников delete(Long id).
        String sql = "DELETE FROM `faculties` WHERE `id` = ?";
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
            } catch (Exception e) {
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

    public Faculty readByName(String name) throws DaoException {
        String sql = "SELECT `id`, `recruitment_plan`,`certificate`, `subject_1`, `subject_2`, `subject_3` FROM `faculties` WHERE `name` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            Faculty faculty = null;
            if (resultSet.next()) {
                faculty = new Faculty();
                faculty.setId(resultSet.getLong("id"));
                faculty.setRecruitment_plan(resultSet.getInt("recruitment_plan"));
                faculty.setCertificate(resultSet.getString("certificate"));
                faculty.setSubject_1(resultSet.getString("subject_1"));
                faculty.setSubject_2(resultSet.getString("subject_2"));
                faculty.setSubject_3(resultSet.getString("subject_3"));
                faculty.setName(name);
            }
            return faculty;
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
    public List<Faculty> readAll() throws DaoException {
        String sql = "SELECT `id`, `name`, `recruitment_plan`,`certificate`, `subject_1`, `subject_2`, `subject_3` FROM `faculties`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            List<Faculty> faculties = new ArrayList<>();
            while (resultSet.next()) {
                Faculty faculty = new Faculty();
                faculty.setId(resultSet.getLong("id"));
                faculty.setName(resultSet.getString("name"));
                faculty.setRecruitment_plan(resultSet.getInt("recruitment_plan"));
                faculty.setCertificate(resultSet.getString("certificate"));
                faculty.setSubject_1(resultSet.getString("subject_1"));
                faculty.setSubject_2(resultSet.getString("subject_2"));
                faculty.setSubject_3(resultSet.getString("subject_3"));
                faculties.add(faculty);
            }
            return faculties;
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
