package ParentAdminApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JsonService {
    private static final String EVENTS_FILE_PATH = "data/events.json";

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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("src/main/resources/json/pupils.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(inputStream, new TypeReference<List<Pupil>>() {});
        } catch (IOException e) {
            System.err.println("Error loading pupils.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Event> loadEvents() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

            File file = new File(EVENTS_FILE_PATH);
            System.out.println("Loading events from: " + file.getAbsolutePath()); // Debug
            if (!file.exists()) {
                System.out.println("Could not find events file at: " + file.getAbsolutePath());
                return new ArrayList<>();
            } else {
                System.out.println("Loaded events file at: " + file.getAbsolutePath());
            }

        try {
        return mapper.readValue(file, new TypeReference<List<Event>>() {}) ;
        } catch (IOException e) {
            System.out.println ("Error reading events json:" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Event> getEventsByCohortId(Integer cohortId) {
        List<Event> allEvents = loadEvents();
        System.out.println("All events before filtering: " + allEvents);
        return allEvents.stream()
                .filter(event -> event.getCohortId().equals(cohortId))
                .collect(Collectors.toList());
    }

    public void saveEvent(Event event) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            List<Event> events = loadEvents();
            if (event.getEventId() == null || event.getEventId().isEmpty()) {
                event.setEventId(UUID.randomUUID().toString());
            }

            events.add(event);
            File file = new File(EVENTS_FILE_PATH);
            mapper.writeValue(file, events);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save event", e);
        }
    }
}


