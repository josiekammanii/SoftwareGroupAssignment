package ParentAdminApplication;
import java.time.LocalDate;

public class Pupil {

    private String id;
    private String name;
    private LocalDate dob;
    private Integer cohortId;

    public String getName(){
        return name;
    }

    public LocalDate getDob(){
        return dob;
    }
}
