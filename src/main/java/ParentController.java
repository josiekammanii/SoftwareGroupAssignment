import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ParentController {


    @PostMapping ("/Login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

        return null;
    }


}
