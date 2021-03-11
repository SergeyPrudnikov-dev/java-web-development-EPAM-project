package by.epam.jvd.vitebsk.task4.domain;

public class Faculty extends Entity {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int recruitment_plan;
    private String certificate;
    private String subject_1;
    private String subject_2;
    private String subject_3;

    public Faculty() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecruitment_plan() {
        return recruitment_plan;
    }

    public void setRecruitment_plan(int recruitment_plan) {
        this.recruitment_plan = recruitment_plan;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getSubject_1() {
        return subject_1;
    }

    public void setSubject_1(String subject_1) {
        this.subject_1 = subject_1;
    }

    public String getSubject_2() {
        return subject_2;
    }

    public void setSubject_2(String subject_2) {
        this.subject_2 = subject_2;
    }

    public String getSubject_3() {
        return subject_3;
    }

    public void setSubject_3(String subject_3) {
        this.subject_3 = subject_3;
    }

    @Override
    public String toString() {
        return "Faculty [name=" + name + ", recruitment_plan=" + recruitment_plan + ", certificate=" + certificate
                + ", subject_1=" + subject_1 + ", subject_2=" + subject_2 + ", subject_3=" + subject_3 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((certificate == null) ? 0 : certificate.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + recruitment_plan;
        result = prime * result + ((subject_1 == null) ? 0 : subject_1.hashCode());
        result = prime * result + ((subject_2 == null) ? 0 : subject_2.hashCode());
        result = prime * result + ((subject_3 == null) ? 0 : subject_3.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Faculty other = (Faculty) obj;
        if (certificate == null) {
            if (other.certificate != null)
                return false;
        } else if (!certificate.equals(other.certificate))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (recruitment_plan != other.recruitment_plan)
            return false;
        if (subject_1 == null) {
            if (other.subject_1 != null)
                return false;
        } else if (!subject_1.equals(other.subject_1))
            return false;
        if (subject_2 == null) {
            if (other.subject_2 != null)
                return false;
        } else if (!subject_2.equals(other.subject_2))
            return false;
        if (subject_3 == null) {
            if (other.subject_3 != null)
                return false;
        } else if (!subject_3.equals(other.subject_3))
            return false;
        return true;
    }
}
