package ParentAdminApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JsonService {

    public Pupil findPupilbyNameAndDob(String pupilName, LocalDateTime dobTime) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/pupils.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            List<Pupil> pupils = mapper.readValue(inputStream, new TypeReference<List<Pupil>>() {});
            LocalDate dob = dobTime.toLocalDate();

            for (Pupil pupil : pupils) {
                if (pupil.getName().trim().equalsIgnoreCase(pupilName.trim()) && pupil.getDob().equals(dob)) {
                    return pupil;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not load or read pupils.json", e);
        }

        return null;
    }

    public List<Pupil> loadPupils() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/pupils.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(inputStream, new TypeReference<List<Pupil>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Could not load or read pupils.json", e);
        }
    }

    public List<Event> loadEvents() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/events.json");
            return file.exists() ? mapper.readValue(file, new TypeReference<List<Event>>() {}) : new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load events", e);
        }
    }

    public List<Event> getEventsByCohortId(Integer cohortId) {
        List<Event> allEvents = loadEvents();
        return allEvents.stream()
                .filter(event -> event.getCohortId().equals(cohortId))
                .collect(Collectors.toList());
    }

    public static void saveEvent(Event event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            File file = new File("src/main/events.json");
            List<Event> events = file.exists()
                    ? mapper.readValue(file, new TypeReference<List<Event>>() {})
                    : new ArrayList<>();

            events.add(event);
            mapper.writeValue(file, events);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save event", e);
        }
    }
}


