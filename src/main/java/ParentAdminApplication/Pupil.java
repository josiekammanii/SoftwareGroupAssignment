package ParentAdminApplication;
import java.time.LocalDate;

public class Pupil {

    private String id;
    private String name;
    private LocalDate dob;
    private String cohortId;

    public String getId() { return id; }
    public void setID(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getCohortId() { return cohortId; }
    public void setCohortId(String cohortId) {this.cohortId = cohortId; }
}
