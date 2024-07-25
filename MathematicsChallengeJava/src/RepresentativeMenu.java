import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class RepresentativeMenu {
    private Connection conn; // Database connection
    private PrintWriter out; // Output stream to send responses
    private Scanner scanner; // Input stream to read commands
    private String filePath; // Path to the file containing participant details

    // Constructor to initialize the RepresentativeMenu with database connection, output stream, input stream, and file path
    public RepresentativeMenu(Connection conn, PrintWriter out, Scanner scanner, String filePath) {
        this.conn = conn;
        this.out = out;
        this.scanner = scanner;
        this.filePath = filePath;
    }

    // Method to display the menu and handle user commands
    public void showMenu() {
        try {
            while (true) {
                // Display available commands
                out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
                out.flush(); // Ensure the prompt is sent to the client

                // Read the command from the user
                String command = scanner.nextLine();
                System.out.println("Representative command received: " + command); // Debugging output

                // Check if the command is null or empty
                if (command == null || command.trim().isEmpty()) {
                    out.println("Invalid command.");
                    out.flush();
                    continue;
                }

                boolean exitMenu = false;
                // Process the command
                switch (command.split(" ")[0]) {
                    case "viewApplicants":
                        viewApplicants(); // Show the list of applicants
                        break;
                    case "confirm":
                        confirmParticipantCommand(command); // Confirm or reject a participant
                        break;
                    case "Logout":
                        out.println("Logged out");
                        out.flush();
                        exitMenu = true; // Exit the menu
                        break;
                    default:
                        out.println("Invalid command.");
                        out.flush();
                }

                if (exitMenu) {
                    break; // Exit the menu loop
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error handling the menu command.");
            out.flush();
        }
    }

    // Method to view applicants from the file
    private void viewApplicants() {
        File file = new File(filePath);
        // Check if the file exists
        if (!file.exists()) {
            out.println("Error: participant_details.txt file not found.");
            out.flush();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean hasData = false;
            // Read and print each line from the file
            while ((line = reader.readLine()) != null) {
                out.println(line);
                hasData = true;
            }
            if (!hasData) {
                out.println("No participant details found in the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("Error reading participant details.");
        }
        out.flush();
    }

    // Method to handle the confirm command
    private void confirmParticipantCommand(String command) {
        String[] details = command.split(" ");
        // Validate command format
        if (details.length != 3) {
            out.println("Invalid command. Usage: confirm yes/no <username>");
            out.flush();
            return;
        }

        String action = details[1]; // Extract the action (yes/no)
        String username = details[2]; // Extract the username
        confirmParticipant(username, action.equals("yes")); // Confirm or reject the participant
    }

    // Method to confirm or reject a participant based on the username
    private void confirmParticipant(String username, boolean accepted) {
        File file = new File(filePath);
        // Check if the file exists
        if (!file.exists()) {
            out.println("Error: participant_details.txt file not found.");
            out.flush();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;
            ParticipantDetails participantDetails = null;

            // Read each line from the file to find the participant
            while ((line = reader.readLine()) != null) {
                // Skip header lines
                if (line.contains("Number")) {
                    continue;
                }

                String[] fields = line.split("\\s+");
                // Check if the line contains enough columns
                if (fields.length < 9) {
                    continue;
                }

                String fileUsername = fields[1];
                // Match the username to find the participant
                if (fileUsername.equals(username)) {
                    found = true;
                    // Create a ParticipantDetails object with the parsed data
                    participantDetails = new ParticipantDetails(
                            fileUsername,
                            fields[2],
                            fields[3],
                            fields[4],
                            fields[5],
                            fields[6],
                            fields[7],
                            fields[8]
                    );
                    break;
                }
            }

            if (!found) {
                out.println("Username not found in the details file.");
                out.flush();
                return;
            }

            // Prepare the query to insert participant details into the appropriate table
            String tableName = accepted ? "acceptedparticipants" : "rejectedparticipants";
            String insertQuery = "INSERT INTO " + tableName + " (username, schoolRegNo, email, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, participantDetails.getUsername());
                pstmt.setString(2, participantDetails.getSchoolRegNo());
                pstmt.setString(3, participantDetails.getEmail());
                pstmt.setString(4, participantDetails.getFirstName());
                pstmt.setString(5, participantDetails.getLastName());
                pstmt.setString(6, participantDetails.getDateOfBirth());
                pstmt.executeUpdate();
                out.println("Participant details successfully added to " + tableName + ".");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Error adding participant details to database: " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("Error reading participant details.");
        }
        out.flush();
    }

    // Inner class to hold participant details
    private class ParticipantDetails {
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String dateOfBirth;
        private String schoolRegNo;
        private String imageFile;
        private String dateTime;

        // Constructor to initialize participant details
        public ParticipantDetails(String username, String firstName, String lastName, String email, String dateOfBirth, String schoolRegNo, String imageFile, String dateTime) {
            this.username = username;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.dateOfBirth = dateOfBirth;
            this.schoolRegNo = schoolRegNo;
            this.imageFile = imageFile;
            this.dateTime = dateTime;
        }

        // Getters for participant details
        public String getUsername() {
            return username;
        }

        public String getSchoolRegNo() {
            return schoolRegNo;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }
    }
}
