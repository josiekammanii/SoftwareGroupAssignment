public class Event {

    private String eventId;
    public String eventName;
    public LocalDateTime eventTime;
    public Integer CohortId ;

    public Event(String eventName, LocalDateTime eventTime, Integer CohortId) {
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.CohortId = CohortId;
    }

    public void changeEventName(String eventName) {
        this.eventName = eventName;
    }

    public void changeEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }
    public void changeCohortId(Integer CohortId) {
        this.CohortId = CohortId;
    }
}
