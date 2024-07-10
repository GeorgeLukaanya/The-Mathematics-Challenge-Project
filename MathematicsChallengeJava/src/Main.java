import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    // Database URL and credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Database connected successfully.");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter command (login <username> <password> or exit): ");
                String command = scanner.nextLine();
                String[] commandParts = command.split(" ");

                if (commandParts.length == 3 && commandParts[0].equalsIgnoreCase("login")) {
                    String username = commandParts[1];
                    String password = commandParts[2];

                    if (isValidLogin(connection, username, password)) {
                        RepresentativeMenu representativeMenu = new RepresentativeMenu(connection);
                        representativeMenu.startMenu();
                    } else {
                        System.out.println("Wrong login details.");
                    }
                } else if (commandParts.length == 1 && commandParts[0].equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program.");
                    break;
                } else {
                    System.out.println("Invalid command. Use login <username> <password> or exit.");
                }
            }

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidLogin(Connection connection, String username, String password) {
        String query = "SELECT * FROM SchoolRepresentative WHERE username = ? AND password = ?";
        try (var pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            var rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
