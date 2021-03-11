package by.epam.jvd.vitebsk.task4.service.faculty;

import java.util.List;

import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.service.ServiceException;

public interface FacultyService {
    Faculty findById(Long id) throws ServiceException;

    void delete(Long id) throws ServiceException;

    void save(Faculty faculty) throws ServiceException;

    List<Faculty> findAll() throws ServiceException;
}
