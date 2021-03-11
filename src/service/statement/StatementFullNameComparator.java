package by.epam.jvd.vitebsk.task4.service.statement;

import java.util.Comparator;

import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;

public class StatementFullNameComparator implements Comparator<StatementByEntrant> {

    @Override
    public int compare(StatementByEntrant s1, StatementByEntrant s2) {
        String fullName1;
        String fullName2;
        fullName1 = s1.getLastName() + s1.getFirstName() + s1.getPatronymic();
        fullName2 = s2.getLastName() + s2.getFirstName() + s2.getPatronymic();
        return fullName1.compareTo(fullName2);
    }
}
