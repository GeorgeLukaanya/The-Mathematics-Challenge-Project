import java.io.*;
import java.sql.*;
import java.util.*;

public class LoginHandler {
    private Connection conn;
    private PrintWriter out;
    private BufferedReader in;

    public LoginHandler(Connection conn, PrintWriter out, BufferedReader in) {
        this.conn = conn;
        this.out = out;
        this.in = in;
    }

    public String processLoginCommand(String command) {
        try {
            String[] parts = command.split(" ");
            if (parts.length != 3) {
                return "Invalid command. Usage: Login <username> <password>";
            }

            String username = parts[1];
            String password = parts[2];

            // Validate login details against the database
            String query = "SELECT password FROM schoolrepresentative WHERE username = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (storedPassword.equals(password)) {
                        return "Login successful";
                    } else {
                        return "Wrong Login details, Try Again";
                    }
                } else {
                    return "Wrong Login details, Try Again";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "Database error during login.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing login command.";
        }
    }
}
