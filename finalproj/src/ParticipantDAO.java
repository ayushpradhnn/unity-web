import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipantDAO {

    public void addParticipant(Participant participant) throws SQLException {
        String query = "INSERT INTO Participant (name, email, event_id) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, participant.getName());
            preparedStatement.setString(2, participant.getEmail());
            preparedStatement.setInt(3, participant.getEventId());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    participant.setParticipantId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<Participant> getAllParticipants() throws SQLException {
        List<Participant> participants = new ArrayList<>();
        String query = "SELECT * FROM Participant";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Participant participant = new Participant(
                        resultSet.getInt("participant_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("event_id")
                );
                participants.add(participant);
            }
        }
        return participants;
    }

    public Participant getParticipantById(int participantId) throws SQLException {
        String query = "SELECT * FROM Participant WHERE participant_id = ?";
        Participant participant = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, participantId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    participant = new Participant(
                            resultSet.getInt("participant_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("event_id")
                    );
                }
            }
        }
        return participant;
    }

    public void updateParticipant(Participant participant) throws SQLException {
        String query = "UPDATE Participant SET name = ?, email = ?, event_id = ? WHERE participant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, participant.getName());
            preparedStatement.setString(2, participant.getEmail());
            preparedStatement.setInt(3, participant.getEventId());
            preparedStatement.setInt(4, participant.getParticipantId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteParticipant(int participantId) throws SQLException {
        String query = "DELETE FROM Participant WHERE participant_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, participantId);
            preparedStatement.executeUpdate();
        }
    }
}
