package ParentAdminApplication;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import ParentAdminApplication.Event;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<Event> loadEvents() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File("src/main/events.json");
            return mapper.readValue(file, new TypeReference<List<Event>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
    public List<Event> getEventsByCohortId(Integer cohortId) {
        List<Event> allEvents = loadEvents();
        return allEvents.stream()
                .filter(event -> event.getCohortId().equals(cohortId))
                .collect(Collectors.toList());
    }

}
