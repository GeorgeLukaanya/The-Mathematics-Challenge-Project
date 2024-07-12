import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ParticipantLogin {
    static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

            boolean loggedIn = false;

            while (!loggedIn) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter your school's registration number: ");
                String schoolREgNo = scanner.nextLine();

                loggedIn = verifyLogin(connection, username, schoolREgNo);

                if (loggedIn) {
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static boolean verifyLogin(Connection connection, String username, String password) throws SQLException {
        String query = "SELECT * FROM AcceptedParticipants WHERE username = ? AND schoolREgNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean isValid = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return isValid;
    }
}

