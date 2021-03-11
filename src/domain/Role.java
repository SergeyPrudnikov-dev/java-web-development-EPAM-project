package by.epam.jvd.vitebsk.task4.domain;

public enum Role {
    ADMIN("role.admin"),
    ENTRANT("role.entrant"),
    STUDENT("role.student"), // эта роль для примера(не реализована на данном этапе)
    CURATOR("role.curator"); // эта роль для примера(не реализована на данном этапе)
    
    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Long getId() {
        return Long.valueOf(ordinal());
    }
}
