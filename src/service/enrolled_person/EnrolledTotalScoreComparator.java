package by.epam.jvd.vitebsk.task4.service.enrolled_person;

import java.util.Comparator;

import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;

public class EnrolledTotalScoreComparator implements Comparator<EnrolledPerson> {

    @Override
    public int compare(EnrolledPerson ep1, EnrolledPerson ep2) { // сортировка по убыванию суммы баллов
        int totalScore1;
        int totalScore2;
        totalScore1 = ep1.getTotalScore();
        totalScore2 = ep2.getTotalScore();

        if (totalScore1 < totalScore2) {
            return 1;
        }
        if (totalScore1 > totalScore2) {
            return -1;
        } else {
            return 0;
        }
    }

}
