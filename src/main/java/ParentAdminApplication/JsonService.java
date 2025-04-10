package ParentAdminApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JsonService {

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

    protected abstract List<Pupil> loadPupils();

    public abstract List<Event> loadEvents();

    public List<Event> getEventsByCohortId(Integer cohortId) {
        List<Event> allEvents = loadEvents();
        return allEvents.stream()
                .filter(event -> event.getCohortId().equals(cohortId))
                .collect(Collectors.toList());
    }
}
