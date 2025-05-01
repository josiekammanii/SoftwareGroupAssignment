package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AdminController {

    private final JsonService jsonService;

    @Autowired
    public AdminController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        try {
            jsonService.saveEvent(event);
            return ResponseEntity.ok("Event saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving event");
        }
    }

        @GetMapping("/events/{cohortId}")
        public ResponseEntity<List<Event>> getEventsByCohort (@PathVariable Integer cohortId){
            List<Event> events = jsonService.getEventsByCohortId(cohortId);

            if (events.isEmpty()) {
                return ResponseEntity.status(404).body(new ArrayList<>());
            }
            return ResponseEntity.ok(events);
        }
    }



