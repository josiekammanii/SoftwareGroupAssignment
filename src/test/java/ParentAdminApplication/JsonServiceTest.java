package ParentAdminApplication;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return Arrays.asList(
                    new Event("1", "Sports Day", LocalDateTime.of(2025, 6, 10, 10, 0).format(formatter), "10:00", "Field", 1),
                    new Event("2", "Science Fair", LocalDateTime.of(2025, 6, 15, 12, 0).format(formatter), "12:00", "Lab", 2)
            );
        }

        @Override
        public List<rsvp> loadRsvps() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return Arrays.asList(
                    new rsvp(1, "2",  "Cinema Trip", "Jane Lois", "Yes"),
                    new rsvp(2, "1", "Science Fair",  "Aisha Rahman", "No")
            );
        }

        @Override
        public Pupil findPupilbyNameAndDob(String pupilName, LocalDateTime dobTime) {
            List<Pupil> pupils = this.loadPupils();
            LocalDate dob = dobTime.toLocalDate();
            for (Pupil pupil : pupils) {
                if (pupil.getName().trim().equalsIgnoreCase(pupilName.trim()) && pupil.getDob().equals(dob)) {
                    return pupil;
                }
            }
            return null;
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

        @Test
        public rsvp getRsvpsByCohortId () {
            List<Pupil> pupils = this.loadPupils();
            LocalDate dob = dobTime.toLocalDate();
            for (Pupil pupil : pupils) {
                if (pupil.getName().trim().equalsIgnoreCase(pupilName.trim()) && pupil.getDob().equals(dob)) {
                    return pupil;
                }
            }
            return null;
        }

    }
