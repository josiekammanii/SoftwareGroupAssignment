package ParentAdminApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class rsvpJsonService {
    private static final String RSVP_FILE_PATH = "data/rsvp.json";//TODO check the file paths and move the jsons to the correct folder

    public List<rsvp> getAllRSVPs() {
        return loadRsvps();
    }

    public static rsvp getRsvpByCohortId(Integer cohortId) {
        try{
            List<rsvp> allRsvps = loadRsvps();
            return (rsvp) allRsvps.stream()
                    .filter(rsvp -> rsvp.getCohortId().equals(cohortId))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static rsvp getRsvpByEventId(String eventId) {
        try{
            List<rsvp> allRsvps = loadRsvps();
            return (rsvp) allRsvps.stream()
                    .filter(rsvp -> rsvp.getEventId().equals(eventId))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static List<rsvp> loadRsvps() {
        try {
            InputStream inputStream = rsvpJsonService.class.getClassLoader().getResourceAsStream(RSVP_FILE_PATH);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.readValue(inputStream, new TypeReference<List<rsvp>>() {});
        } catch (IOException e) {
            System.err.println("Error loading rsvp.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @GetMapping("/rsvps/saveRsvp")
    public void saveRsvp(rsvp rsvp) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            List<rsvp> rsvps = loadRsvps();
            if (rsvp.getRsvpId() == null || rsvp.getPupilName().isEmpty()) {
                rsvp.setRsvpId();
            }

            System.out.println("New rsvp to add: " + rsvps);
            rsvps.add(rsvp);
            System.out.println("Rsvps after adding: " + rsvp);
            File file = new File(RSVP_FILE_PATH);
            System.out.println("Saving rsvp to: " + file.getAbsolutePath());
            mapper.writeValue(file, rsvps);
            System.out.println("Rsvps saved successfully");
        } catch (IOException e) {
            System.err.println("Failed to save rsvp: " + e.getMessage());
            throw new RuntimeException("Failed to save rsvp", e);
        }
    }

    @GetMapping("/rsvps/updateRsvp")
    public void updateRsvp(rsvp rsvp) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            List<rsvp> rsvps = loadRsvps();
            boolean updatedRsvp = false;
            for (int i = 0; i < getAllRSVPs().size(); i++) {
                if (rsvps.get(i).getRsvpId().equals(rsvp.getRsvpId())) {
                    rsvps.set(i, rsvp);
                    updatedRsvp = true;
                    break;
                }
            }
            if (!updatedRsvp) {
                System.out.println("There is no rsvp with ID " + rsvp.getRsvpId() + ". Rsvp cannot be updated.");
                return;
            }

            System.out.println("Updated rsvp: " + rsvp);
            System.out.println("Rsvps after updating: " + rsvps);
            File file = new File(RSVP_FILE_PATH);
            System.out.println("Saving rsvp to: " + file.getAbsolutePath());
            mapper.writeValue(file, rsvps);
            System.out.println("Rsvps saved successfully");
        } catch (IOException e) {
            System.err.println("Failed to update rsvp: " + e.getMessage());
            throw new RuntimeException("Failed to update rsvp", e);
        }
    }
}


