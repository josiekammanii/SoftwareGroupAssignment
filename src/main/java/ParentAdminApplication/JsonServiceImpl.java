package ParentAdminApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JsonServiceImpl extends JsonService {

    @Override
    protected List<Pupil> loadPupils() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/pupils.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(inputStream, new TypeReference<List<Pupil>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Could not load or read pupils.json", e);
        }
    }

    @Override
    public List<Event> loadEvents() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            File file = new File("src/main/events.json");
            return mapper.readValue(file, new TypeReference<List<Event>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

