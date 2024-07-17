import java.io.*;
import java.sql.*;

// This code handles the representative menu
public class RepresentativeMenu {
    private Connection conn;
    private PrintWriter out;
    private BufferedReader in;
    private String filePath;

    public RepresentativeMenu(Connection conn, PrintWriter out, BufferedReader in, String filePath) {
        this.conn = conn;
        this.out = out;
        this.in = in;
        this.filePath = filePath;
    }

    public void showMenu() {
        try {
            while (true) {
                out.println("Enter a command: \n1. viewApplicants\n2. confirm yes/no <username>\n3. Logout");
                out.flush(); // Ensure the output is sent to the client
                String command = in.readLine();
                if (command.equals("1")) {
                    viewApplicants();
                } else if (command.startsWith("2 ")) {
                    String[] details = command.split(" ");
                    if (details.length != 3) {
                        out.println("Invalid command. Usage: confirm yes/no <username>");
                    } else {
                        String action = details[1];
                        String username = details[2];
                        confirmParticipant(username, action.equals("yes"));
                    }
                } else if (command.equals("3")) {
                    out.println("Logged out");
                    return;
                } else {
                    out.println("Invalid command.");
                }
                out.flush(); // Ensure the output is sent to the client
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewApplicants() {
        File file = new File(filePath);
        if (!file.exists()) {
            out.println("Error: participant_details.txt file not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
    }

    private void confirmParticipant(String username, boolean accepted) {
        File file = new File(filePath);
        if (!file.exists()) {
            out.println("Error: participant_details.txt file not found.");
            return;
        }

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

            String tableName = accepted ? "AcceptedParticipants" : "RejectedParticipants";
            String insertQuery = "INSERT INTO " + tableName + " (username, firstname, lastname, emailAddress, date_of_birth, registration_number, image_file) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, username);
                pstmt.setString(2, getValueFromFile(reader, "First Name: "));
                pstmt.setString(3, getValueFromFile(reader, "Last Name: "));
                pstmt.setString(4, getValueFromFile(reader, "Email Address: "));
                pstmt.setString(5, getValueFromFile(reader, "Date of Birth: "));
                pstmt.setString(6, getValueFromFile(reader, "Registration Number: "));
                pstmt.setString(7, getValueFromFile(reader, "Image File Path: "));
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    out.println("Participant " + (accepted ? "accepted" : "rejected") + " successfully.");
                } else {
                    out.println("Error: Participant not found.");
                }
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
                return line.substring(key.length()).trim();
            }
        }
        return "";
    }
}
