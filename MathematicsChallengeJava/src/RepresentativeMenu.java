import java.io.*;
import java.sql.*;

public class RepresentativeMenu {

    private Connection conn;
    private PrintWriter out;
    private BufferedReader in;
    private RegistrationHandler registrationHandler;

    public RepresentativeMenu(Connection conn, PrintWriter out, BufferedReader in, RegistrationHandler registrationHandler) {
        this.conn = conn;
        this.out = out;
        this.in = in;
        this.registrationHandler = registrationHandler;
    }

    public void showMenu() throws IOException {
        while (true) {
            out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
            String command = in.readLine();

            if (command.equals("viewApplicants")) {
                viewApplicants();
            } else if (command.startsWith("confirm ")) {
                confirmParticipant(command);
            } else if (command.equals("Logout")) {
                out.println("Logged out");
                return;
            } else {
                out.println("Invalid command.");
            }
        }
    }

    private void viewApplicants() {
        String filePath = "participant_details.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } catch (FileNotFoundException e) {
            out.println("Error: participant_details.txt file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            out.println("Error reading participant details.");
        }
    }

    private void confirmParticipant(String command) {
        String[] details = command.split(" ");
        if (details.length != 3) {
            out.println("Invalid command. Usage: confirm yes/no <username>");
            return;
        }

        String action = details[1];
        String username = details[2];

        if (action.equals("yes")) {
            moveParticipant(username, "AcceptedParticipants");
        } else if (action.equals("no")) {
            moveParticipant(username, "RejectedParticipants");
        } else {
            out.println("Invalid action. Use yes or no.");
        }
    }

    private void moveParticipant(String username, String tableName) {
        String filePath = "participant_details.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
                return;
            }

            String insertQuery = "INSERT INTO " + tableName + " (username, firstname, lastname, emailAddress, date_of_birth, registration_number, image_file) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, username);
                pstmt.setString(2, getValueFromFile(reader, "First Name: "));
                pstmt.setString(3, getValueFromFile(reader, "Last Name: "));
                pstmt.setString(4, getValueFromFile(reader, "Email Address: "));
                pstmt.setString(5, getValueFromFile(reader, "Date of Birth: "));
                pstmt.setString(6, getValueFromFile(reader, "Registration Number: "));
                pstmt.setString(7, getValueFromFile(reader, "Image File Path: "));
                pstmt.executeUpdate();
                out.println("Participant details moved to " + tableName);
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("Error moving participant details.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            out.println("Error reading participant details.");
        }
    }

    private String getValueFromFile(BufferedReader reader, String key) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(key)) {
                return line.substring(key.length());
            }
        }
        return "";
    }
}
