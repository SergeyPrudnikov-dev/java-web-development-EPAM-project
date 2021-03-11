package by.epam.jvd.vitebsk.task4.domain;

import java.util.Date;

public class StatementByEntrant extends Entity { // TODO это DTO-обект, заявление которое подают абитуриенты
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String passportId;
    private Faculty faculty;
    private int certificateScore;
    private int subjectScore1;
    private int subjectScore2;
    private int subjectScore3;
    private Date date;
    private long userId;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getCertificateScore() {
        return certificateScore;
    }

    public void setCertificateScore(int certificateScore) {
        this.certificateScore = certificateScore;
    }

    public int getSubjectScore1() {
        return subjectScore1;
    }

    public void setSubjectScore1(int subjectScore1) {
        this.subjectScore1 = subjectScore1;
    }

    public int getSubjectScore2() {
        return subjectScore2;
    }

    public void setSubjectScore2(int subjectScore2) {
        this.subjectScore2 = subjectScore2;
    }

    public int getSubjectScore3() {
        return subjectScore3;
    }

    public void setSubjectScore3(int subjectScore3) {
        this.subjectScore3 = subjectScore3;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StatementByEntrant [lastName=" + lastName + ", firstName=" + firstName + ", patronymic=" + patronymic
                + ", passportId=" + passportId + ", faculty=" + faculty + ", certificateScore=" + certificateScore
                + ", subjectScore1=" + subjectScore1 + ", subjectScore2=" + subjectScore2 + ", subjectScore3="
                + subjectScore3 + ", date=" + date + ", userId=" + userId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + certificateScore;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((passportId == null) ? 0 : passportId.hashCode());
        result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
        result = prime * result + subjectScore1;
        result = prime * result + subjectScore2;
        result = prime * result + subjectScore3;
        result = prime * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StatementByEntrant other = (StatementByEntrant) obj;
        if (certificateScore != other.certificateScore)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (faculty == null) {
            if (other.faculty != null)
                return false;
        } else if (!faculty.equals(other.faculty))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (passportId == null) {
            if (other.passportId != null)
                return false;
        } else if (!passportId.equals(other.passportId))
            return false;
        if (patronymic == null) {
            if (other.patronymic != null)
                return false;
        } else if (!patronymic.equals(other.patronymic))
            return false;
        if (subjectScore1 != other.subjectScore1)
            return false;
        if (subjectScore2 != other.subjectScore2)
            return false;
        if (subjectScore3 != other.subjectScore3)
            return false;
        if (userId != other.userId)
            return false;
        return true;
    }
}
