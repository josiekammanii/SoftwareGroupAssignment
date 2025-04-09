package ParentAdminApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping
public class ParentController {

@Autowired
    private PupilJsonService pupilJsonService;

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

}
