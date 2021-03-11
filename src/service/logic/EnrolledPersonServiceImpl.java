package by.epam.jvd.vitebsk.task4.service.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.dao.DaoException;
import by.epam.jvd.vitebsk.task4.dao.EnrolledPersonDao;
import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledFullNameComparator;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledPersonService;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledTotalScoreComparator;

public class EnrolledPersonServiceImpl extends BaseService implements EnrolledPersonService {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());
    private EnrolledPersonDao enrPersonDao;

    public void setEnrolledPersonDao(EnrolledPersonDao enrPersonDao) {
        this.enrPersonDao = enrPersonDao;
    }

    @Override
    public List<EnrolledPerson> findAll() throws ServiceException {
        try {
            return enrPersonDao.readAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public EnrolledPerson findById(Long id) throws ServiceException {
        try {
            return enrPersonDao.read(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(EnrolledPerson enrolledPerson) throws ServiceException {
        try {
            getTransaction().start();
            if (enrolledPerson.getId() != null) {
                enrPersonDao.update(enrolledPerson);
            } else {
                Long id = enrPersonDao.create(enrolledPerson);
                enrolledPerson.setId(id);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw e;
        }
    }

    @Override
    public void saveAll(List<EnrolledPerson> enrolledPersons) throws ServiceException {
        try {
            getTransaction().start();

            for (EnrolledPerson enrolledPerson : enrolledPersons) {
                if (enrolledPerson.getId() != null) {
                    enrPersonDao.update(enrolledPerson);
                } else {
                    Long id = enrPersonDao.create(enrolledPerson);
                    enrolledPerson.setId(id);
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
    public List<EnrolledPerson> findByFullName(String fullName) throws ServiceException {
        try {
            return enrPersonDao.readByFullName(fullName);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            enrPersonDao.delete(id);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteAll(List<EnrolledPerson> enrolledPersons) throws ServiceException {
        try {
            getTransaction().start();
            for (EnrolledPerson enrolledPerson : enrolledPersons) {
                enrPersonDao.delete(enrolledPerson.getId());
            }
            getTransaction().commit();
        } catch (DaoException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            logger.error(e);
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
                logger.error(e);
            }
            throw e;
        }
    }

    @Override
    public List<EnrolledPerson> createEnrolledList(Map<Long, List<StatementByEntrant>> mapStatements,
            Map<Long, Integer> facultyIdRecruitmentPlan) throws ServiceException {

        List<EnrolledPerson> tempEnrolledPersons;
        List<EnrolledPerson> enrolledPersons;
        int tempRecruitmentPlan;
        Long tempIdFaculty;
        List<StatementByEntrant> tempStatementList;
        enrolledPersons = new ArrayList<>();

        // перебираем в Map списки заявленых абитуриентов отдельно по факультетам
        for (Map.Entry<Long, List<StatementByEntrant>> item : mapStatements.entrySet()) {
            tempIdFaculty = item.getKey();
            tempStatementList = item.getValue();
            tempRecruitmentPlan = facultyIdRecruitmentPlan.get(tempIdFaculty);

            // формируем списки поданных заявлений в виде EnrolledPersons (зачисленные
            // люди), на данном этапе здесь все абитуриенты (не поступившие удалятся позже)
            tempEnrolledPersons = createTempEnrolledList(tempStatementList);

            // зачисление абитуриентов в рамках данного факультета
            enrollmentEntrantsInFaculty(tempEnrolledPersons, tempRecruitmentPlan);

            // добавляем зачисленных абитуриентов в общий список зачисленных
            enrolledPersons.addAll(tempEnrolledPersons);
        }
        return enrolledPersons;
    }

    private List<EnrolledPerson> createTempEnrolledList(List<StatementByEntrant> tempStatementList)
            throws ServiceException {
        EnrolledPerson tempEnrolledPerson;
        List<EnrolledPerson> tempList;
        String tempFullName;
        int tempTotalScore;
        tempList = new ArrayList<>();
        for (StatementByEntrant statement : tempStatementList) {
            tempEnrolledPerson = new EnrolledPerson();
            tempFullName = statement.getLastName() + " " + statement.getFirstName() + " " + statement.getPatronymic();
            tempEnrolledPerson.setFullName(tempFullName);
            tempTotalScore = statement.getCertificateScore() + statement.getSubjectScore1()
                    + statement.getSubjectScore2() + statement.getSubjectScore3();
            tempEnrolledPerson.setTotalScore(tempTotalScore);
            tempEnrolledPerson.setFacultyId(statement.getFaculty().getId());
            tempEnrolledPerson.setStatementId(statement.getId());
            tempList.add(tempEnrolledPerson);
        }
        return tempList;
    }

    private void enrollmentEntrantsInFaculty(List<EnrolledPerson> enrolledPersons, int recruitmentPlan)
            throws ServiceException { // метод удаляет из списка всех, кто не набрал проходной балл
        sortByTotalScore(enrolledPersons); // отсортировали по убыванию суммы баллов
        int passingScore; // проходной балл

        if (recruitmentPlan < enrolledPersons.size()) { // если план приема меньше поданных заявлений(абитуриентов на
                                                        // этот факультет)
            passingScore = enrolledPersons.get(recruitmentPlan - 1).getTotalScore();

            ListIterator<EnrolledPerson> listIter = enrolledPersons.listIterator();
            while (listIter.hasNext()) {
                if (listIter.next().getTotalScore() < passingScore) {
                    listIter.remove(); // удаляем из списка всех у кого сумма баллов меньше проходного
                }
            } // здесь я умышленно зачисляю всех, у кого сумма баллов >= проходного балла
        } // даже если в итоге зачисленных будет больше плана набора
    }

    @Override
    public void sortByTotalScore(List<EnrolledPerson> enrolledPersons) throws ServiceException {
        Comparator<EnrolledPerson> eComparator = new EnrolledTotalScoreComparator()
                .thenComparing(new EnrolledFullNameComparator());
        Collections.sort(enrolledPersons, eComparator);
    }

    @Override
    public void sortByFullName(List<EnrolledPerson> enrolledPersons) throws ServiceException {
        Comparator<EnrolledPerson> eComparator = new EnrolledFullNameComparator()
                .thenComparing(new EnrolledTotalScoreComparator());
        Collections.sort(enrolledPersons, eComparator);
    }
}
