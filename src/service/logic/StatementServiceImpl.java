package by.epam.jvd.vitebsk.task4.service.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.dao.DaoException;
import by.epam.jvd.vitebsk.task4.dao.StatementDao;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.statement.StatementFacultyNameComparator;
import by.epam.jvd.vitebsk.task4.service.statement.StatementFullNameComparator;
import by.epam.jvd.vitebsk.task4.service.statement.StatementPassportIdNotUniqueException;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class StatementServiceImpl extends BaseService implements StatementService {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private StatementDao statementDao;

    public void setStatementDao(StatementDao statementDao) {
        this.statementDao = statementDao;
    }

    @Override
    public void save(StatementByEntrant statementEnt) throws ServiceException {
        try {
            getTransaction().start();
            if (statementEnt.getId() != null) {
                statementDao.update(statementEnt);
            } else {
                if (statementDao.readBypassportId(statementEnt.getPassportId()) == null) { // проверка на уникальность
                                                                                           // номера пасспорта при
                                                                                           // создании нового заявления
                    Long id = statementDao.create(statementEnt);
                    statementEnt.setId(id);
                } else {
                    logger.error("StatementPassportIdNotUniqueException");
                    throw new StatementPassportIdNotUniqueException(statementEnt.getId());
                }
            }
            getTransaction().commit();
        } catch (DaoException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e1);
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e1);
            }
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            statementDao.delete(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public StatementByEntrant findById(Long id) throws ServiceException {
        try {
            return statementDao.read(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<StatementByEntrant> findAll() throws ServiceException {
        try {
            return statementDao.readAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void sortByFacultyName(List<StatementByEntrant> statements) throws ServiceException {
        Comparator<StatementByEntrant> sComparator = new StatementFacultyNameComparator()
                .thenComparing(new StatementFullNameComparator());
        Collections.sort(statements, sComparator);
    }

    @Override
    public void sortByFullName(List<StatementByEntrant> statements) throws ServiceException {
        Comparator<StatementByEntrant> sComparator = new StatementFullNameComparator();
        Collections.sort(statements, sComparator);
    }

    @Override
    public StatementByEntrant findByUserId(Long userId) throws ServiceException {
        try {
            return statementDao.readByUserId(userId);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Map<Long, List<StatementByEntrant>> separateStatementsByFaculty(List<StatementByEntrant> statementsAll)
            throws ServiceException {

        List<Long> idFaculties;
        Map<Long, List<StatementByEntrant>> mapStatements;
        List<StatementByEntrant> statementsFaculty;
        idFaculties = new ArrayList<>();
        mapStatements = new HashMap<Long, List<StatementByEntrant>>();

        statementsAll.forEach(statement -> { // создаем список id факультетов, которые указаны в заявлениях
            Long idFaculty = statement.getFaculty().getId();
            if (!idFaculties.contains(idFaculty)) {
                idFaculties.add(idFaculty);
            }
        });

        for (Long idFaculty : idFaculties) {
            statementsFaculty = new ArrayList<>();
            for (StatementByEntrant statement : statementsAll) { // перебираем все заявления и добавляем в отдельный
                                                                 // список
                if (statement.getFaculty().getId().equals(idFaculty)) { // заявления с текущим факультетом faculty
                    statementsFaculty.add(statement);
                }
            }
            mapStatements.put(idFaculty, statementsFaculty);
        }
        return mapStatements;
    }
}
