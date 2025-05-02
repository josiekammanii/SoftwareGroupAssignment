package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/events")
public class AdminController {

    private final JsonService jsonService;

    @Autowired
    public AdminController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> events = jsonService.loadEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            System.err.println("Error fetching events: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
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

        @PutMapping("/{eventId}")
        public ResponseEntity<String> updateEvent(@PathVariable String eventId, @RequestBody Event event) {
            try {
                event.setEventId(eventId);
                System.out.println("Received updated event: " + event);
                jsonService.updateEvent(event);
                return ResponseEntity.ok("Event updated successfully");
            } catch (Exception e) {
                System.err.println("Error updating event: " + e.getMessage());
                return ResponseEntity.status(500).body("Error updating event");
            }
        }

        @DeleteMapping("/{eventId}")
        public ResponseEntity<String> deleteEvent(@PathVariable String eventId) {
            try {
                System.out.println("Deleting event with ID: " + eventId);
                jsonService.deleteEvent(eventId);
                return ResponseEntity.ok("Event deleted successfully");
            } catch (Exception e) {
                System.err.println("Error deleting event: " + e.getMessage());
                return ResponseEntity.status(500).body("Error deleting event");
            }
        }
    }



