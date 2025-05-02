package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ParentController {

    @Autowired
    private JsonService jsonService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String name = loginRequest.getName();
        LocalDate dob = loginRequest.getDOB();

        System.out.println("Received name: " + name);
        System.out.println("Received dob: " + dob);

        try {
            Pupil pupil = jsonService.findPupilbyNameAndDob(name, dob.atStartOfDay());
            if (pupil == null) {
                return ResponseEntity.status(401).body("This pupil does not exist");
            }
            return ResponseEntity.ok(pupil);
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            return ResponseEntity.status(500).body("Error processing login request: " + e.getMessage());
        }
    }

    @GetMapping("/parents-events/{cohortId}")
    public ResponseEntity<?> getEventsByCohort(@PathVariable Integer cohortId) {
        List<Event> events = jsonService.getEventsByCohortId(cohortId);
        return ResponseEntity.ok(events);
    }

}
