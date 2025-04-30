package ParentAdminApplication;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import ParentAdminApplication.Event;

import static org.junit.jupiter.api.Assertions.*;

class JsonServiceTest {

    JsonService jsonService = new JsonService() {
        @Override
        public List<Pupil> loadPupils() {
            return Arrays.asList(
                    new Pupil("James Radcliffe", LocalDate.of(2005, 4, 23), 1),
                    new Pupil("Olu Smith", LocalDate.of(2005, 7, 21), 2)
            );
        }

        @Override
        public List<Event> loadEvents() {
            return Arrays.asList(
                    new Event("Sports Day", LocalDateTime.of(2025, 6, 10, 10, 0), 1),
                    new Event("Science Fair", LocalDateTime.of(2025, 6, 15, 12, 0), 2)
            );
        }
    };

    @Test
    void findPupilbyNameAndDob() {
        String name = "James Radcliffe";
        LocalDateTime dob = LocalDate.of(2005, 4, 23).atStartOfDay();

        Pupil result = jsonService.findPupilbyNameAndDob(name, dob);

        assertNotNull(result);
        assertEquals("James Radcliffe", result.getName());
        assertEquals(LocalDate.of(2005, 4, 23), result.getDob());
    }

}