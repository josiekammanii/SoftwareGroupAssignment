package ParentAdminApplication;
import java.time.LocalDate;

public class Pupil {

    private String id;
    private String name;
    private LocalDate dob;
    private String cohortId;

    public String getId() { return id; }
    public void setID(String id) { this.id = id; }

    public String getName(){
        return name;
    }

    public LocalDate getDob(){
        return dob;
    }
}
