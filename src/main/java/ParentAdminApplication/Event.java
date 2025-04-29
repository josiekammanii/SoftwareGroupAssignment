package ParentAdminApplication;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

    public class Event {

        private String eventId;
        private String eventName;

        @JsonFormat(pattern = "dd-MM-yyyy")
        private String eventDate;

        private String eventTime;
        private String location;
        private Integer cohortId;

        public Event() {
        }

        public Event(String eventId, String eventName, String eventDate, String eventTime, String location, Integer cohortId) {
            this.eventId = eventId;
            this.eventName = eventName;
            this.eventDate = eventDate;
            this.eventTime = eventTime;
            this.location = location;
            this.cohortId = cohortId;
        }

        public String getEventId() {
            return eventId;
        }

        public String getEventName() {
            return eventName;
        }

        public String getEventDate() {
            return eventDate;
        }

        public String getEventTime() {
            return eventTime;
        }

        public String getLocation() {
            return location;
        }

        public Integer getCohortId() {
            return cohortId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public void setEventDate(String eventDate) {
            this.eventDate = eventDate;
        }

        public void setEventTime(String eventTime) {
            this.eventTime = eventTime;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setCohortId(Integer cohortId) {
            this.cohortId = cohortId;
        }
    }


