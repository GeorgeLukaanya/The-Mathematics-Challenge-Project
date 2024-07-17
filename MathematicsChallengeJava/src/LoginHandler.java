import java.io.*;
import java.sql.*;

// This code processes the logins of the representative
public class LoginHandler {
    private Connection conn;
    private PrintWriter out;
    private BufferedReader in;

    public LoginHandler(Connection conn, PrintWriter out, BufferedReader in, String filePath) {
        this.conn = conn;
        this.out = out;
        this.in = in;
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
}
