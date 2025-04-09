package ParentAdminApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PupilJsonService {

    private final ObjectMapper ObjectMapper;

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
