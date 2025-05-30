package ParentAdminApplication;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

    public class Event {

        private String eventId;
        private String eventName;
        @JsonFormat(pattern = "DD-MM-YYYY")
        private String eventDate;
        private String eventTime;
        private String eventLocation;
        private Integer cohortId;

        public Event() {}

        public Event(String eventId, String eventName, String eventDate, String eventTime, String eventLocation, Integer cohortId) {
            this.eventId = eventId;
            this.eventName = eventName;
            this.eventDate = eventDate;
            this.eventTime = eventTime;
            this.eventLocation = eventLocation;
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

        public String getEventLocation() {
            return eventLocation;
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
            if (eventTime != null && eventTime.contains("T")) {
                // Handle combined format "2025-06-07T16:30"
                String[] parts = eventTime.split("T");
                if (parts.length == 2) {
                    // Convert "2025-06-07" to "07-06-2025"
                    String[] dateParts = parts[0].split("-");
                    this.eventDate = String.format("%s-%s-%s", dateParts[2], dateParts[1], dateParts[0]);
                    // Extract "16:30"
                    this.eventTime = parts[1];
                }
            } else {
                this.eventTime = eventTime;
            }
        }

        public void setEventLocation(String eventLocation) {
            this.eventLocation = eventLocation;
        }

        public void setCohortId(Integer cohortId) {
            this.cohortId = cohortId;
        }
    }


