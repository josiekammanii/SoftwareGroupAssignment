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
    private final ObjectMapper objectMapper;

    public PupilJsonService(ObjectMapper objectMapper) {
    this.ObjectMapper = new ObjectMapper();
    this.ObjectMapper.registerModule(new JavaTimeModule());
    this.ObjectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper = objectMapper;
    }

    public List<Pupil> readPupils() throws IOException {
        File file = new File("pupils.json");
        if (!file.exists()) return new ArrayList<>();
        return ObjectMapper.readValue(file, new TypeReference<List<Pupil>>() {});
        }

    public Pupil findPupilbyNameAndDob(String pupilName, LocalDate dob) {
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
