import java.io.*;
import java.sql.*;

public class LoginHandler {
    private Connection conn;
    private PrintWriter out;
    private BufferedReader in;
    private String filePath;

    public LoginHandler(Connection conn, PrintWriter out, BufferedReader in, String filePath) {
        this.conn = conn;
        this.out = out;
        this.in = in;
        this.filePath = filePath;
    }

    public String processLoginCommand(String command) {
        String[] details = command.split(" ");
        if (details.length != 3) {
            return "Invalid command. Usage: Login <username> <password>";
        }

        String username = details[1];
        String password = details[2];

        String query = "SELECT * FROM SchoolRepresentative WHERE username = ? AND password = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return "Login successful";
            } else {
                return "Invalid username or password.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error processing login.";
        }
    }

    public void showMenu() {
        try {
            while (true) {
                out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
                String command = in.readLine();
                if (command.equals("viewApplicants")) {
                    viewApplicants();
                } else if (command.startsWith("confirm ")) {
                    String[] details = command.split(" ");
                    if (details.length != 3) {
                        out.println("Invalid command. Usage: confirm yes/no <username>");
                    } else {
                        String action = details[1];
                        String username = details[2];
                        confirmParticipant(username, action.equals("yes"));
                    }
                } else if (command.equals("Logout")) {
                    out.println("Logged out");
                    return;
                } else {
                    out.println("Invalid command.");
                }
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