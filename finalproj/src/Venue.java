public class Venue {
    private int venueId;         // Unique identifier for the venue
    private String venueName;    // Name of the venue
    private String location;     // Location of the venue
    private int capacity;        // Maximum capacity of the venue

    // Constructor for creating a new Venue (without ID)
    public Venue(String venueName, String location, int capacity) {
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
    }

    // Constructor for retrieving or updating Venue (with ID)
    public Venue(int venueId, String venueName, String location, int capacity) {
        this.venueId = venueId;
        this.venueName = venueName;
        this.location = location;
        this.capacity = capacity;
    }

    // Getter and Setter for venueId (useful when ID is generated later by the DB)
    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    // Getter and Setter for venueName
    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    // Getter and Setter for location
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Getter and Setter for capacity
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // toString method to display venue details in a readable format
    @Override
    public String toString() {
        return "Venue ID: " + venueId + ", Name: " + venueName + ", Location: " + location + ", Capacity: " + capacity;
    }
}
