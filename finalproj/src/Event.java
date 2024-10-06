public class Event {
    private int eventId;           // Event ID (typically set after insertion into DB)
    private String eventName;      // Name of the event
    private String eventDate;      // Date of the event
    private int venueId;           // Venue ID for the event

    // Constructor without event ID (for creating new events)
    public Event(String eventName, String eventDate, int venueId) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venueId = venueId;
    }

    // Constructor with event ID (for retrieving/updating events)
    public Event(int eventId, String eventName, String eventDate, int venueId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venueId = venueId;
    }

    // Getter for event ID
    public int getEventId() {
        return eventId;
    }

    // Setter for event ID (this will be used when the event ID is returned after insertion)
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    // Getter for event name
    public String getEventName() {
        return eventName;
    }

    // Setter for event name
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    // Getter for event date
    public String getEventDate() {
        return eventDate;
    }

    // Setter for event date
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    // Getter for venue ID
    public int getVenueId() {
        return venueId;
    }

    // Setter for venue ID
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    // Override toString method for better event display
    @Override
    public String toString() {
        return "Event ID: " + eventId + ", Name: " + eventName + ", Date: " + eventDate + ", Venue ID: " + venueId;
    }
}
