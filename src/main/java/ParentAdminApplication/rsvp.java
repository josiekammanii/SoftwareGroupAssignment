package ParentAdminApplication;

import java.time.LocalDateTime;
import java.util.Random;

public class rsvp {
    private static Integer rsvpId;
    private Integer cohortId;
    private String eventId;
    private String eventName;
    private static String pupilName;
    private LocalDateTime rsvpDateTime;
    private String rsvpResponse;

    public rsvp(Integer cohortId, String eventId, String eventName, String pupilName, String rsvpResponse) {
        this.cohortId = cohortId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.pupilName = pupilName;
        this.rsvpResponse = rsvpResponse;
    }

    //Getters
    public Integer getCohortId() {
        return cohortId;
    }

    public static Integer getRsvpId() {
        return rsvpId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public static String getPupilName() {
        return pupilName;
    }

    public LocalDateTime getRsvpDateTime() {
        return rsvpDateTime;
    }

    public String getRsvpResponse(){
        return rsvpResponse;
    }

    //Setters
    public void setRsvpId() {
        if (rsvpId == null && rsvp.getPupilName().isEmpty()) {
            Random r = new Random();
            Integer rsvpId = r.nextInt(1000000);
        } else {
            this.rsvpId = rsvpId;
        }
    }
}