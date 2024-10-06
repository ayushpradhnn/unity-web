import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();
        ParticipantDAO participantDAO = new ParticipantDAO();
        VenueDAO venueDAO = new VenueDAO();

        while (true) {
            System.out.println("\nEvent Management System");
            System.out.println("1. Add Event");
            System.out.println("2. View All Events");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Add Participant");
            System.out.println("6. View All Participants");
            System.out.println("7. Update Participant");
            System.out.println("8. Delete Participant");
            System.out.println("9. Add Venue");
            System.out.println("10. View All Venues");
            System.out.println("11. Update Venue");
            System.out.println("12. Delete Venue");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addEvent(scanner, eventDAO);
                        break;
                    case 2:
                        viewAllEvents(eventDAO);
                        break;
                    case 3:
                        updateEvent(scanner, eventDAO);
                        break;
                    case 4:
                        deleteEvent(scanner, eventDAO);
                        break;
                    case 5:
                        addParticipant(scanner, participantDAO);
                        break;
                    case 6:
                        viewAllParticipants(participantDAO);
                        break;
                    case 7:
                        updateParticipant(scanner, participantDAO);
                        break;
                    case 8:
                        deleteParticipant(scanner, participantDAO);
                        break;
                    case 9:
                        addVenue(scanner, venueDAO);
                        break;
                    case 10:
                        viewAllVenues(venueDAO);
                        break;
                    case 11:
                        updateVenue(scanner, venueDAO);
                        break;
                    case 12:
                        deleteVenue(scanner, venueDAO);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Methods for handling events
    private static void addEvent(Scanner scanner, EventDAO eventDAO) throws SQLException {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        String eventDate = scanner.nextLine();
        System.out.print("Enter venue ID: ");
        int venueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Event event = new Event(eventName, eventDate, venueId);
        eventDAO.addEvent(event);
        System.out.println("Event added successfully with ID: " + event.getEventId());
    }

    private static void viewAllEvents(EventDAO eventDAO) throws SQLException {
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            System.out.println(event);
        }
    }

    private static void updateEvent(Scanner scanner, EventDAO eventDAO) throws SQLException {
        System.out.print("Enter event ID to update: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Event event = eventDAO.getEventById(eventId);
        if (event != null) {
            System.out.print("Enter new event name (leave blank to keep current: " + event.getEventName() + "): ");
            String eventName = scanner.nextLine();
            if (!eventName.isBlank()) {
                event.setEventName(eventName);
            }
            System.out.print("Enter new event date (YYYY-MM-DD) (leave blank to keep current: " + event.getEventDate() + "): ");
            String eventDate = scanner.nextLine();
            if (!eventDate.isBlank()) {
                event.setEventDate(eventDate);
            }
            System.out.print("Enter new venue ID (leave blank to keep current: " + event.getVenueId() + "): ");
            String venueInput = scanner.nextLine();
            if (!venueInput.isBlank()) {
                int venueId = Integer.parseInt(venueInput);
                event.setVenueId(venueId);
            }
            eventDAO.updateEvent(event);
            System.out.println("Event updated successfully!");
        } else {
            System.out.println("Event not found.");
        }
    }

    private static void deleteEvent(Scanner scanner, EventDAO eventDAO) throws SQLException {
        System.out.print("Enter event ID to delete: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        eventDAO.deleteEvent(eventId);
        System.out.println("Event deleted successfully.");
    }

    // Methods for handling participants
    private static void addParticipant(Scanner scanner, ParticipantDAO participantDAO) throws SQLException {
        System.out.print("Enter participant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter participant email: ");
        String email = scanner.nextLine();
        System.out.print("Enter event ID: ");
        int eventId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Participant participant = new Participant(name, email, eventId);
        participantDAO.addParticipant(participant);
        System.out.println("Participant added successfully with ID: " + participant.getParticipantId());
    }

    private static void viewAllParticipants(ParticipantDAO participantDAO) throws SQLException {
        List<Participant> participants = participantDAO.getAllParticipants();
        for (Participant participant : participants) {
            System.out.println(participant);
        }
    }

    private static void updateParticipant(Scanner scanner, ParticipantDAO participantDAO) throws SQLException {
        System.out.print("Enter participant ID to update: ");
        int participantId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Participant participant = participantDAO.getParticipantById(participantId);
        if (participant != null) {
            System.out.print("Enter new participant name (leave blank to keep current: " + participant.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.isBlank()) {
                participant.setName(name);
            }
            System.out.print("Enter new email (leave blank to keep current: " + participant.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.isBlank()) {
                participant.setEmail(email);
            }
            System.out.print("Enter new event ID (leave blank to keep current: " + participant.getEventId() + "): ");
            String eventInput = scanner.nextLine();
            if (!eventInput.isBlank()) {
                int eventId = Integer.parseInt(eventInput);
                participant.setEventId(eventId);
            }
            participantDAO.updateParticipant(participant);
            System.out.println("Participant updated successfully!");
        } else {
            System.out.println("Participant not found.");
        }
    }

    private static void deleteParticipant(Scanner scanner, ParticipantDAO participantDAO) throws SQLException {
        System.out.print("Enter participant ID to delete: ");
        int participantId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        participantDAO.deleteParticipant(participantId);
        System.out.println("Participant deleted successfully.");
    }

    // Methods for handling venues
    private static void addVenue(Scanner scanner, VenueDAO venueDAO) throws SQLException {
        System.out.print("Enter venue name: ");
        String venueName = scanner.nextLine();
        System.out.print("Enter venue location: ");
        String location = scanner.nextLine();
        System.out.print("Enter venue capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Venue venue = new Venue(venueName, location, capacity);
        venueDAO.addVenue(venue);
        System.out.println("Venue added successfully with ID: " + venue.getVenueId());
    }

    private static void viewAllVenues(VenueDAO venueDAO) throws SQLException {
        List<Venue> venues = venueDAO.getAllVenues();
        for (Venue venue : venues) {
            System.out.println(venue);
        }
    }

    private static void updateVenue(Scanner scanner, VenueDAO venueDAO) throws SQLException {
        System.out.print("Enter venue ID to update: ");
        int venueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Venue venue = venueDAO.getVenueById(venueId);
        if (venue != null) {
            System.out.print("Enter new venue name (leave blank to keep current: " + venue.getVenueName() + "): ");
            String venueName = scanner.nextLine();
            if (!venueName.isBlank()) {
                venue.setVenueName(venueName);
            }
            System.out.print("Enter new location (leave blank to keep current: " + venue.getLocation() + "): ");
            String location = scanner.nextLine();
            if (!location.isBlank()) {
                venue.setLocation(location);
            }
            System.out.print("Enter new capacity (leave blank to keep current: " + venue.getCapacity() + "): ");
            String capacityInput = scanner.nextLine();
            if (!capacityInput.isBlank()) {
                int capacity = Integer.parseInt(capacityInput);
                venue.setCapacity(capacity);
            }
            venueDAO.updateVenue(venue);
            System.out.println("Venue updated successfully!");
        } else {
            System.out.println("Venue not found.");
        }
    }

    private static void deleteVenue(Scanner scanner, VenueDAO venueDAO) throws SQLException {
        System.out.print("Enter venue ID to delete: ");
        int venueId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        venueDAO.deleteVenue(venueId);
        System.out.println("Venue deleted successfully.");
    }
}
