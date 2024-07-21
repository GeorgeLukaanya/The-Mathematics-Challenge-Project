import java.sql.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class LoginHandler {
    private Connection connection;
    private PrintWriter out;
    private Scanner scanner;
    private RepresentativeMenu representativeMenu;

    public LoginHandler(Connection connection, PrintWriter out, Scanner scanner, RepresentativeMenu representativeMenu) {
        this.connection = connection;
        this.out = out;
        this.scanner = scanner;
        this.representativeMenu = representativeMenu;
    }

    public String processLoginCommand(String command) {
        String[] commandParts = command.split(" ");
        if (commandParts.length == 3) {
            String username = commandParts[1];
            String password = commandParts[2];

            try {
                if (verifyRepresentativeLogin(username, password)) {
                    out.println("Representative login successful");
                    representativeMenu.showMenu();
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

    private boolean verifyRepresentativeLogin(String username, String password) throws SQLException {
        String query = "SELECT * FROM `math-challengez`.schoolrepresentative WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isValid = resultSet.next();
            resultSet.close();

            return isValid;
        }
    }
}
