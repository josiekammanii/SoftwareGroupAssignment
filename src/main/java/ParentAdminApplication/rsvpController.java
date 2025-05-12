package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class rsvpController {

    private final rsvpJsonService rsvpJsonService;

    @Autowired
    public rsvpController(rsvpJsonService rsvpJsonService) {
        this.rsvpJsonService = rsvpJsonService;
    }

    @GetMapping("/rsvps/{cohortId}")
    public ResponseEntity<List<rsvp>> getRsvpByCohortId(@PathVariable Integer cohortId) {
        try {
            List<rsvp> rsvps = (List<rsvp>) rsvpJsonService.getRsvpByCohortId(cohortId);
            return ResponseEntity.ok(rsvps);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}

