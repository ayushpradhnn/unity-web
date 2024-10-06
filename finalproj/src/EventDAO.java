import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    // Add Event (with generated keys to get the event ID)
    public void addEvent(Event event) throws SQLException {
        String query = "INSERT INTO Event (event_name, event_date, venue_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventDate());
            preparedStatement.setInt(3, event.getVenueId());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    event.setEventId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Get All Events
    public List<Event> getAllEvents() throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM Event";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("event_name"),
                        resultSet.getString("event_date"),
                        resultSet.getInt("venue_id")
                );
                events.add(event);
            }
        }
        return events;
    }

    // Get Event by ID
    public Event getEventById(int eventId) throws SQLException {
        String query = "SELECT * FROM Event WHERE event_id = ?";
        Event event = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    event = new Event(
                            resultSet.getInt("event_id"),
                            resultSet.getString("event_name"),
                            resultSet.getString("event_date"),
                            resultSet.getInt("venue_id")
                    );
                }
            }
        }
        return event;
    }

    // Update Event
    public void updateEvent(Event event) throws SQLException {
        String query = "UPDATE Event SET event_name = ?, event_date = ?, venue_id = ? WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, event.getEventName());
            preparedStatement.setString(2, event.getEventDate());
            preparedStatement.setInt(3, event.getVenueId());
            preparedStatement.setInt(4, event.getEventId());
            preparedStatement.executeUpdate();
        }
    }

    // Delete Event
    public void deleteEvent(int eventId) throws SQLException {
        String query = "DELETE FROM Event WHERE event_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventId);
            preparedStatement.executeUpdate();
        }
    }
}
