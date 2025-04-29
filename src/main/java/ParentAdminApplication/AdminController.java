package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminController {

    private final JsonService jsonService;

    @Autowired
    public AdminController(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @PostMapping("/events")
    public ResponseEntity<String> createEvent(@RequestBody Event) {
        try {
            jsonService.saveEvent(Event);
            return ResponseEntity.ok("Event saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving event");
        }
    }
}

