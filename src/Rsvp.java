public class Rsvp {

    private String rsvpId;
    private String pupilId
    private String eventId;
    private boolean attending;

    public Rsvp(String pupilId, String eventId , boolean attending) {
        this.pupilId = pupilId;
        this.eventId = eventId;
        this.attending = attending;
    }

}
