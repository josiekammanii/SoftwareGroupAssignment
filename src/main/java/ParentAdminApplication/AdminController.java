package ParentAdminApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class AdminController {

    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        try {
            JsonService.saveEvent(event);
            return ResponseEntity.ok("Event saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving event");
        }
    }

}
