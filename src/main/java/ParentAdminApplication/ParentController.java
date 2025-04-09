package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping
public class ParentController {

    @GetMapping("/")
    public String hello() {
        return "Backend is working!";
    }

    @Autowired
    private JsonService jsonService;

    @PostMapping ("/Login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        String name = loginRequest.getName();
        LocalDate dob = loginRequest.getDOB();

        Pupil pupil = jsonService.findPupilbyNameAndDob(name, dob.atStartOfDay());

        if (pupil == null) {
            return ResponseEntity.status(401).body("This pupil does not exist");
        } else {
            return ResponseEntity.ok(pupil);
        }

    }
    @GetMapping("/api/events/{cohortId}")
    public ResponseEntity<?> getEventsByCohort(@PathVariable Integer cohortId) {
        List<Event> events = jsonService.getEventsByCohortId(cohortId);
        return ResponseEntity.ok(events);
    }



}
