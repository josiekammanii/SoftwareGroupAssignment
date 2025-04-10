package ParentAdminApplication;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Pupil {

    private String id;
    private String name;
    private LocalDate dob;
    private Integer cohortId;

    public Pupil(String name, LocalDate dob, Integer cohortId) {
        this.name = name;
        this.dob = dob;
        this.cohortId = cohortId;
    }

    public Pupil(){

    }
    public String getName(){
        return name;
    }

    public LocalDate getDob(){
        return dob;
    }
}
