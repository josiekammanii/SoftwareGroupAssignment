package ParentAdminApplication;

import java.util.Random;

public class rsvp {
    private static Integer rsvpId;
    private Integer cohortId;
    private String eventId;
    private String eventName;
    private String pupilName;
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

    public String getRsvpId() {
        return pupilName + eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getPupilName() {
        return pupilName;
    }

    public String getRsvpResponse(){
        return rsvpResponse;
    }
    public String setRsvpId() {
        return pupilName + eventId;
    }
}