import java.io.*;
import java.sql.*;

public class LoginHandler {

    private Connection conn;
    private PrintWriter out;
    private BufferedReader in;
    private RegistrationHandler registrationHandler;

    public LoginHandler(Connection conn, PrintWriter out, BufferedReader in, RegistrationHandler registrationHandler) {
        this.conn = conn;
        this.out = out;
        this.in = in;
        this.registrationHandler = registrationHandler;
    }

    public String processLoginCommand(String command) {
        String[] details = command.split(" ");
        if (details.length != 3) {
            return "Invalid number of details. Usage: Login <username> <password>";
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

    public void showMenu() throws IOException {
        RepresentativeMenu representativeMenu = new RepresentativeMenu(conn, out, in, registrationHandler);
        representativeMenu.showMenu();
    }
}
