public class Participant {
    private int participantId;
    private String name;
    private String email;
    private int eventId;

    // Constructor without participant ID (for creating new participants)
    public Participant(String name, String email, int eventId) {
        this.name = name;
        this.email = email;
        this.eventId = eventId;
    }

    // Constructor with participant ID (for retrieving/updating participants)
    public Participant(int participantId, String name, String email, int eventId) {
        this.participantId = participantId;
        this.name = name;
        this.email = email;
        this.eventId = eventId;
    }

    // Getters and setters
    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        return "Participant ID: " + participantId + ", Name: " + name + ", Email: " + email + ", Event ID: " + eventId;
    }
}
