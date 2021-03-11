package by.epam.jvd.vitebsk.task4.controller.enrolledperson;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.controller.LoginAction;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledPersonService;

public class EnrolledPersonListAction extends Action {
    static Logger logger = LogManager.getLogger(LoginAction.class.getName());

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("work");
        try {
            EnrolledPersonService enrolledService;
            List<EnrolledPerson> enrolledPersons;
            enrolledService = getServiceFactory().getEnrolledPersonService();
            enrolledPersons = enrolledService.findAll();

            /*
             * if (enrolledPersons.isEmpty()) { StatementService statementService;
             * FacultyService facultyService;
             * 
             * List<StatementByEntrant> statements; Map<Long, List<StatementByEntrant>>
             * mapStatements;
             * 
             * List<Faculty> allFaculties; Map<Long, Integer> facultyIdRecruitmentPlan;
             * 
             * statementService = getServiceFactory().getStatementService(); facultyService
             * = getServiceFactory().getFacultyService(); enrolledService =
             * getServiceFactory().getEnrolledPersonServiceWithTransaction();
             * 
             * statements = statementService.findAll(); // все поданные заявления
             * 
             * allFaculties = facultyService.findAll(); facultyIdRecruitmentPlan = new
             * HashMap<Long, Integer>(); allFaculties.forEach(faculty -> {
             * facultyIdRecruitmentPlan.put(faculty.getId(), faculty.getRecruitment_plan());
             * }); // здесь мы создали Map (id факультета - план набора) mapStatements =
             * statementService.separateStatementsByFaculty(statements); // все поданные
             * заявления, // разделенные по факультетам enrolledPersons =
             * enrolledService.createEnrolledList(mapStatements, facultyIdRecruitmentPlan);
             * try { enrolledService.saveAll(enrolledPersons); } catch (ServiceException e)
             * { logger.error(e); throw new ServletException(e); } }
             */

            req.setAttribute("enrolledPersons", enrolledPersons);
        } catch (ServiceFactoryException | ServiceException e) {
            logger.error(e);
            throw new ServletException(e);
        }
        return null;
    }
}
