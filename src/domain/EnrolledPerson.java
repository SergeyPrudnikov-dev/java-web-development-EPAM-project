package by.epam.jvd.vitebsk.task4.domain;

public class EnrolledPerson extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String fullName;
    private int totalScore;
    private long facultyId;
    private long statementId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getStatementId() {
        return statementId;
    }

    public void setStatementId(long statementId) {
        this.statementId = statementId;
    }

    @Override
    public String toString() {
        return "EnrolledPerson [fullName=" + fullName + ", totalScore=" + totalScore + ", facultyId=" + facultyId
                + ", statementId=" + statementId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (facultyId ^ (facultyId >>> 32));
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + (int) (statementId ^ (statementId >>> 32));
        result = prime * result + totalScore;
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
        EnrolledPerson other = (EnrolledPerson) obj;
        if (facultyId != other.facultyId)
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (statementId != other.statementId)
            return false;
        if (totalScore != other.totalScore)
            return false;
        return true;
    }
}
