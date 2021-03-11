package by.epam.jvd.vitebsk.task4.service.enrolled_person;

import java.util.Comparator;

import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;

public class EnrolledFullNameComparator implements Comparator<EnrolledPerson> {

    @Override
    public int compare(EnrolledPerson s1, EnrolledPerson s2) {
        String fullName1;
        String fullName2;
        fullName1 = s1.getFullName();
        fullName2 = s2.getFullName();
        return fullName1.compareTo(fullName2);
    }
}
