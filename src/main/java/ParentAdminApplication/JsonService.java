package ParentAdminApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class JsonService {

    public Pupil findPupilbyNameAndDob(String pupilName, LocalDateTime dob) {
        try {
            File file = new File("pupils.json");
            ObjectMapper mapper = new ObjectMapper();
            List<Pupil> pupils = mapper.readValue(file, new TypeReference<List<Pupil>>() {});

            for (Pupil pupil : pupils){
                if (Objects.equals(pupil.getName(), pupilName) && pupil.getDob().equals(dob)){
                    return pupil;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
