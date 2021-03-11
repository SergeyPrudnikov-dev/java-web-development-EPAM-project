package by.epam.jvd.vitebsk.task4.service.statement;

import java.util.Comparator;

import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;

public class StatementFacultyNameComparator implements Comparator<StatementByEntrant> {

    @Override
    public int compare(StatementByEntrant s1, StatementByEntrant s2) {
        String facultyName1;
        String facultyName2;
        facultyName1 = s1.getFaculty().getName();
        facultyName2 = s2.getFaculty().getName();
        return facultyName1.compareTo(facultyName2);
    }
}
