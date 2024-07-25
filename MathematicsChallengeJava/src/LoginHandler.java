import java.sql.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class LoginHandler {
    // Class variables for database connection, output stream, input scanner, and representative menu
    private Connection connection;
    private PrintWriter out;
    private Scanner scanner;
    private RepresentativeMenu representativeMenu;

    // Constructor to initialize the class variables
    public LoginHandler(Connection connection, PrintWriter out, Scanner scanner, RepresentativeMenu representativeMenu) {
        this.connection = connection;
        this.out = out;
        this.scanner = scanner;
        this.representativeMenu = representativeMenu;
    }

    // Method to process the login command
    public String processLoginCommand(String command) {
        String[] commandParts = command.split(" ");

        // Check if the command has the correct format
        if (commandParts.length == 3) {
            String username = commandParts[1];
            String password = commandParts[2];

            try {
                // Verify the login credentials
                if (verifyRepresentativeLogin(username, password)) {
                    out.println("Representative login successful");
                    representativeMenu.showMenu(); // Show the menu if login is successful
                    return "Representative login successful";
                } else {
                    return "Invalid username or password.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return "An error occurred during login.";
            }
        } else {
            return "Invalid command format. Use: Login <username> <password>";
        }
    }

    // Method to verify the representative login credentials
    private boolean verifyRepresentativeLogin(String username, String password) throws SQLException {
        String query = "SELECT * FROM `math-challengez`.schoolrepresentative WHERE username = ? AND password = ?";

        // Prepare the SQL statement to prevent SQL injection
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isValid = resultSet.next(); // Check if the credentials match
            resultSet.close();

            return isValid;
        }
    }
}
