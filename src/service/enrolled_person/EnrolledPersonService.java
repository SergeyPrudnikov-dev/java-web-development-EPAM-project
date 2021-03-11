package by.epam.jvd.vitebsk.task4.service.enrolled_person;

import java.util.List;
import java.util.Map;

import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;

public interface EnrolledPersonService {
    List<EnrolledPerson> findAll() throws ServiceException;

    EnrolledPerson findById(Long id) throws ServiceException;

    void save(EnrolledPerson enrolledPerson) throws ServiceException;

    void saveAll(List<EnrolledPerson> enrolledPersons) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void deleteAll(List<EnrolledPerson> enrolledPersons) throws ServiceException;

    List<EnrolledPerson> findByFullName(String fullName) throws ServiceException;

    List<EnrolledPerson> createEnrolledList(Map<Long, List<StatementByEntrant>> mapStatements,
            Map<Long, Integer> facultyIdRecruitmentPlan) throws ServiceException;

    void sortByTotalScore(List<EnrolledPerson> enrolledPersons) throws ServiceException;

    void sortByFullName(List<EnrolledPerson> enrolledPersons) throws ServiceException;
}
