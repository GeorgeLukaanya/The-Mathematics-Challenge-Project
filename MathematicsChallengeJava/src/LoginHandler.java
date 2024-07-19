import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class LoginHandler {
    private Connection conn;
    private PrintWriter out;
    private Scanner scanner;

    public LoginHandler(Connection conn, PrintWriter out, Scanner scanner) {
        this.conn = conn;
        this.out = out;
        this.scanner = scanner;
    }

    public String processLoginCommand(String command) {
        try {
            String[] parts = command.split(" ");
            if (parts.length != 3) {
                return "Invalid command. Usage: Login <username> <password>";
            }

            String username = parts[1];
            String password = parts[2];

            // Validate user credentials
            String query = "SELECT * FROM schoolrepresentative WHERE username = ? AND password = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return "Login successful";
                    } else {
                        return "Invalid username or password";
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error during login";
        }
    }
}
