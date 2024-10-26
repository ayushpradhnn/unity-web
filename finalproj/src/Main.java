import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Main extends JFrame {
    private EventDAO eventDAO;
    private ParticipantDAO participantDAO;
    private VenueDAO venueDAO;

    public Main() {
        eventDAO = new EventDAO();
        participantDAO = new ParticipantDAO();
        venueDAO = new VenueDAO();

        setTitle("UNITY-WEB");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JButton eventsButton = new JButton("Events");
        JButton participantsButton = new JButton("Participants");
        JButton venuesButton = new JButton("Venues");
        JButton exitButton = new JButton("Exit");

        eventsButton.addActionListener(e -> showEventOptions());
        participantsButton.addActionListener(e -> showParticipantOptions());
        venuesButton.addActionListener(e -> showVenueOptions());
        exitButton.addActionListener(e -> System.exit(0));

        add(eventsButton);
        add(participantsButton);
        add(venuesButton);
        add(exitButton);
    }

    private void showEventOptions() {
        String[] options = {"Add Event", "View All Events", "Update Event", "Delete Event"};
        int choice = JOptionPane.showOptionDialog(this, "Select an option:", "Event Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> addEventUI();
            case 1 -> viewAllEventsUI();
            case 2 -> updateEventUI();
            case 3 -> deleteEventUI();
        }
    }

    private void showParticipantOptions() {
        String[] options = {"Add Participant", "View All Participants", "Update Participant", "Delete Participant"};
        int choice = JOptionPane.showOptionDialog(this, "Select an option:", "Participant Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> addParticipantUI();
            case 1 -> viewAllParticipantsUI();
            case 2 -> updateParticipantUI();
            case 3 -> deleteParticipantUI();
        }
    }

    private void showVenueOptions() {
        String[] options = {"Add Venue", "View All Venues", "Update Venue", "Delete Venue"};
        int choice = JOptionPane.showOptionDialog(this, "Select an option:", "Venue Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> addVenueUI();
            case 1 -> viewAllVenuesUI();
            case 2 -> updateVenueUI();
            case 3 -> deleteVenueUI();
        }
    }

    // Event methods
    private void addEventUI() {
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField venueIdField = new JTextField();

        Object[] message = {
                "Event Name:", nameField,
                "Event Date (YYYY-MM-DD):", dateField,
                "Venue ID:", venueIdField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Event", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String eventName = nameField.getText();
                String eventDate = dateField.getText();
                int venueId = Integer.parseInt(venueIdField.getText());

                Event event = new Event(eventName, eventDate, venueId);
                eventDAO.addEvent(event);
                JOptionPane.showMessageDialog(this, "Event added successfully with ID: " + event.getEventId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void viewAllEventsUI() {
        try {
            StringBuilder eventsList = new StringBuilder("All Events:\n");
            for (Event event : eventDAO.getAllEvents()) {
                eventsList.append(event.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, eventsList.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateEventUI() {
        String eventId = JOptionPane.showInputDialog(this, "Enter Event ID to update:");
        if (eventId != null && !eventId.isEmpty()) {
            try {
                Event event = eventDAO.getEventById(Integer.parseInt(eventId));
                if (event != null) {
                    JTextField nameField = new JTextField(event.getEventName());
                    JTextField dateField = new JTextField(event.getEventDate());
                    JTextField venueIdField = new JTextField(String.valueOf(event.getVenueId()));

                    Object[] message = {
                            "Event Name:", nameField,
                            "Event Date (YYYY-MM-DD):", dateField,
                            "Venue ID:", venueIdField
                    };

                    int option = JOptionPane.showConfirmDialog(this, message, "Update Event", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        event.setEventName(nameField.getText());
                        event.setEventDate(dateField.getText());
                        event.setVenueId(Integer.parseInt(venueIdField.getText()));
                        eventDAO.updateEvent(event);
                        JOptionPane.showMessageDialog(this, "Event updated successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Event not found.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void deleteEventUI() {
        String eventId = JOptionPane.showInputDialog(this, "Enter Event ID to delete:");
        if (eventId != null && !eventId.isEmpty()) {
            try {
                eventDAO.deleteEvent(Integer.parseInt(eventId));
                JOptionPane.showMessageDialog(this, "Event deleted successfully.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    // Participant methods
    private void addParticipantUI() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField eventIdField = new JTextField();

        Object[] message = {
                "Participant Name:", nameField,
                "Participant Email:", emailField,
                "Event ID:", eventIdField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Participant", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String email = emailField.getText();
                int eventId = Integer.parseInt(eventIdField.getText());

                Participant participant = new Participant(name, email, eventId);
                participantDAO.addParticipant(participant);
                JOptionPane.showMessageDialog(this, "Participant added successfully with ID: " + participant.getParticipantId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void viewAllParticipantsUI() {
        try {
            StringBuilder participantsList = new StringBuilder("All Participants:\n");
            for (Participant participant : participantDAO.getAllParticipants()) {
                participantsList.append(participant.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, participantsList.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateParticipantUI() {
        String participantId = JOptionPane.showInputDialog(this, "Enter Participant ID to update:");
        if (participantId != null && !participantId.isEmpty()) {
            try {
                Participant participant = participantDAO.getParticipantById(Integer.parseInt(participantId));
                if (participant != null) {
                    JTextField nameField = new JTextField(participant.getName());
                    JTextField emailField = new JTextField(participant.getEmail());
                    JTextField eventIdField = new JTextField(String.valueOf(participant.getEventId()));

                    Object[] message = {
                            "Participant Name:", nameField,
                            "Participant Email:", emailField,
                            "Event ID:", eventIdField
                    };

                    int option = JOptionPane.showConfirmDialog(this, message, "Update Participant", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        participant.setName(nameField.getText());
                        participant.setEmail(emailField.getText());
                        participant.setEventId(Integer.parseInt(eventIdField.getText()));
                        participantDAO.updateParticipant(participant);
                        JOptionPane.showMessageDialog(this, "Participant updated successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Participant not found.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void deleteParticipantUI() {
        String participantId = JOptionPane.showInputDialog(this, "Enter Participant ID to delete:");
        if (participantId != null && !participantId.isEmpty()) {
            try {
                participantDAO.deleteParticipant(Integer.parseInt(participantId));
                JOptionPane.showMessageDialog(this, "Participant deleted successfully.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    // Venue methods
    private void addVenueUI() {
        JTextField nameField = new JTextField();
        JTextField locationField = new JTextField();
        JTextField capacityField = new JTextField();

        Object[] message = {
                "Venue Name:", nameField,
                "Location:", locationField,
                "Capacity:", capacityField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Venue", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String location = locationField.getText();
                int capacity = Integer.parseInt(capacityField.getText());

                Venue venue = new Venue(name, location, capacity);
                venueDAO.addVenue(venue);
                JOptionPane.showMessageDialog(this, "Venue added successfully with ID: " + venue.getVenueId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void viewAllVenuesUI() {
        try {
            StringBuilder venuesList = new StringBuilder("All Venues:\n");
            for (Venue venue : venueDAO.getAllVenues()) {
                venuesList.append(venue.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(this, venuesList.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateVenueUI() {
        String venueId = JOptionPane.showInputDialog(this, "Enter Venue ID to update:");
        if (venueId != null && !venueId.isEmpty()) {
            try {
                Venue venue = venueDAO.getVenueById(Integer.parseInt(venueId));
                if (venue != null) {
                    JTextField nameField = new JTextField(venue.getVenueName());
                    JTextField locationField = new JTextField(venue.getLocation());
                    JTextField capacityField = new JTextField(String.valueOf(venue.getCapacity()));

                    Object[] message = {
                            "Venue Name:", nameField,
                            "Location:", locationField,
                            "Capacity:", capacityField
                    };

                    int option = JOptionPane.showConfirmDialog(this, message, "Update Venue", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        venue.setVenueName(nameField.getText());
                        venue.setLocation(locationField.getText());
                        venue.setCapacity(Integer.parseInt(capacityField.getText()));
                        venueDAO.updateVenue(venue);
                        JOptionPane.showMessageDialog(this, "Venue updated successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Venue not found.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void deleteVenueUI() {
        String venueId = JOptionPane.showInputDialog(this, "Enter Venue ID to delete:");
        if (venueId != null && !venueId.isEmpty()) {
            try {
                venueDAO.deleteVenue(Integer.parseInt(venueId));
                JOptionPane.showMessageDialog(this, "Venue deleted successfully.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main mainUI = new Main();
            mainUI.setVisible(true);
        });
    }
}
