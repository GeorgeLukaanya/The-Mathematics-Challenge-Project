import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class RepresentativeMenu {
    private Connection conn;
    private PrintWriter out;
    private Scanner scanner;
    private String filePath;

    public RepresentativeMenu(Connection conn, PrintWriter out, Scanner scanner, String filePath) {
        this.conn = conn;
        this.out = out;
        this.scanner = scanner;
        this.filePath = filePath;
    }

    public void showMenu() {
        try {
            while (true) {
                out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
                out.flush(); // Ensure the prompt is sent to the client

                String command = scanner.nextLine();
                System.out.println("Representative command received: " + command); // Debugging output

                if (command == null || command.trim().isEmpty()) {
                    out.println("Invalid command.");
                    out.flush();
                    continue;
                }

                boolean exitMenu = false;
                switch (command.split(" ")[0]) {
                    case "viewApplicants":
                        viewApplicants();
                        break;
                    case "confirm":
                        confirmParticipantCommand(command);
                        break;
                    case "Logout":
                        out.println("Logged out");
                        out.flush();
                        exitMenu = true;
                        break;
                    default:
                        out.println("Invalid command.");
                        out.flush();
                }

                if (exitMenu) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error handling the menu command.");
            out.flush();
        }
    }

    private void viewApplicants() {
        File file = new File(filePath);
        if (!file.exists()) {
            out.println("Error: participant_details.txt file not found.");
            out.flush();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean hasData = false;
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

    private void confirmParticipantCommand(String command) {
        String[] details = command.split(" ");
        if (details.length != 3) {
            out.println("Invalid command. Usage: confirm yes/no <username>");
            out.flush();
            return;
        }

        String action = details[1];
        String username = details[2];
        confirmParticipant(username, action.equals("yes"));
    }

    private void confirmParticipant(String username, boolean accepted) {
        File file = new File(filePath);
        if (!file.exists()) {
            out.println("Error: participant_details.txt file not found.");
            out.flush();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                out.println("Username not found in the details file.");
                out.flush();
                return;
            }

            String tableName = accepted ? "AcceptedParticipants" : "RejectedParticipants";
            String insertQuery = "INSERT INTO " + tableName + " (username, firstname, lastname, emailAddress, date_of_birth, registration_number, image_file) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, username);
                pstmt.setString(2, getValueFromFile("First Name", username));
                pstmt.setString(3, getValueFromFile("Last Name", username));
                pstmt.setString(4, getValueFromFile("Email Address", username));
                pstmt.setString(5, getValueFromFile("Date of Birth", username));
                pstmt.setString(6, getValueFromFile("School Registration Number", username));
                pstmt.setString(7, getValueFromFile("Image File", username));
                pstmt.executeUpdate();
                out.println("Participant details successfully added to " + tableName + ".");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Error adding participant details to database.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            out.println("Error reading participant details.");
        }
        out.flush();
    }

    private String getValueFromFile(String key, String username) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username: " + username)) {
                    found = true;
                }
                if (found && line.startsWith(key + ": ")) {
                    return line.substring((key + ": ").length());
                }
            }
        }
        return "";
    }
}
