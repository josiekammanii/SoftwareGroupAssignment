package ParentAdminApplication;
import ParentAdminApplication.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EventController {

    private final JsonService jsonService;

    @Autowired
    public EventController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping("/parents-events/{cohortId}")
    public ResponseEntity<List<Event>> getEventsByCohort(@PathVariable Integer cohortID) {
        try {
            List<Event> events = jsonService.getEventsByCohortId(cohortID);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
           return ResponseEntity.status(500).build();
        }
        }
    }

