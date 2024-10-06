import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {

    // Add a new venue to the database
    public void addVenue(Venue venue) throws SQLException {
        String query = "INSERT INTO Venue (venue_name, location, capacity) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, venue.getVenueName());
            preparedStatement.setString(2, venue.getLocation());
            preparedStatement.setInt(3, venue.getCapacity());
            preparedStatement.executeUpdate();

            // Retrieve the generated venue ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    venue.setVenueId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Retrieve all venues from the database
    public List<Venue> getAllVenues() throws SQLException {
        List<Venue> venues = new ArrayList<>();
        String query = "SELECT * FROM Venue";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Venue venue = new Venue(
                        resultSet.getInt("venue_id"),
                        resultSet.getString("venue_name"),
                        resultSet.getString("location"),
                        resultSet.getInt("capacity")
                );
                venues.add(venue);
            }
        }
        return venues;
    }

    // Retrieve a specific venue by its ID
    public Venue getVenueById(int venueId) throws SQLException {
        String query = "SELECT * FROM Venue WHERE venue_id = ?";
        Venue venue = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, venueId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    venue = new Venue(
                            resultSet.getInt("venue_id"),
                            resultSet.getString("venue_name"),
                            resultSet.getString("location"),
                            resultSet.getInt("capacity")
                    );
                }
            }
        }
        return venue;
    }

    // Update venue details in the database
    public void updateVenue(Venue venue) throws SQLException {
        String query = "UPDATE Venue SET venue_name = ?, location = ?, capacity = ? WHERE venue_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, venue.getVenueName());
            preparedStatement.setString(2, venue.getLocation());
            preparedStatement.setInt(3, venue.getCapacity());
            preparedStatement.setInt(4, venue.getVenueId());
            preparedStatement.executeUpdate();
        }
    }

    // Delete a venue from the database
    public void deleteVenue(int venueId) throws SQLException {
        String query = "DELETE FROM Venue WHERE venue_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, venueId);
            preparedStatement.executeUpdate();
        }
    }
}
