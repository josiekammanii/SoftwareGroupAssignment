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

    private final eventJsonService eventJsonService;
    private final rsvpJsonService rsvpJsonService;

    @Autowired
    public AdminController(eventJsonService eventJsonService, ParentAdminApplication.rsvpJsonService rsvpJsonService) {
        this.eventJsonService = eventJsonService;
        this.rsvpJsonService = rsvpJsonService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
            List<Event> events = eventJsonService.loadEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            System.err.println("Error fetching events: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<rsvp>> getAllRsvps() {
        try {
            List<rsvp> rsvps = ParentAdminApplication.rsvpJsonService.loadRsvps();
            return ResponseEntity.ok(rsvps);
        } catch (Exception e) {
            System.err.println("Error fetching rsvps: " + e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        try {
            eventJsonService.saveEvent(event);
            return ResponseEntity.ok("Event saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving event");
        }
    }


        @GetMapping("/events/{cohortId}")
        public ResponseEntity<List<Event>> getEventsByCohort (@PathVariable Integer cohortId){
            List<Event> events = eventJsonService.getEventsByCohortId(cohortId);

            if (events.isEmpty()) {
                return ResponseEntity.status(404).body(new ArrayList<>());
            }
            return ResponseEntity.ok(events);
        }

    @GetMapping("/rsvps/{cohortId}")//TODO check this
    public ResponseEntity<List<rsvp>> getRsvpsByCohortId (@PathVariable Integer cohortId){
        List<rsvp> rsvps = (List<rsvp>) ParentAdminApplication.rsvpJsonService.getRsvpByCohortId(cohortId);

        if (rsvps.isEmpty()) {
            return ResponseEntity.status(404).body(new ArrayList<>());
        }
        return ResponseEntity.ok(rsvps);
    }

        @PutMapping("/{eventId}")
        public ResponseEntity<String> updateEvent(@PathVariable String eventId, @RequestBody Event event) {
            try {
                event.setEventId(eventId);
                System.out.println("Received updated event: " + event);
                eventJsonService.updateEvent(event);
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
                eventJsonService.deleteEvent(eventId);
                return ResponseEntity.ok("Event deleted successfully");
            } catch (Exception e) {
                System.err.println("Error deleting event: " + e.getMessage());
                return ResponseEntity.status(500).body("Error deleting event");
            }
        }

        @GetMapping("/rsvps/{eventId}")
        public ResponseEntity<List<rsvp>> getRSVPsByEventId (@PathVariable String eventId) {
            List<rsvp> rsvps = (List<rsvp>) rsvpJsonService.getRsvpByEventId(eventId);
            return ResponseEntity.ok(rsvps);
        }
    }



