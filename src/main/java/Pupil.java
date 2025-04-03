import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.time.LocalDate;

@Document(collection = "pupils")

public class Pupil {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
    private Integer cohortId;


}
