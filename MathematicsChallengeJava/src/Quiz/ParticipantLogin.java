package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.PrintWriter;

public class ParticipantLogin {
    static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
    static final String USER = "root";
    static final String PASS = "";

    public String handleLogin(Connection connection, String username, String schoolRegNo, PrintWriter out, Scanner scanner) throws SQLException {
        if (verifyLogin(connection, username, schoolRegNo)) {
            out.println("Participant login successful");
            // Proceed with next steps for the participant
            while (true) {
                out.println("Enter command: viewchallenges");
                String viewCommand = scanner.nextLine();
                if (viewCommand.equalsIgnoreCase("viewchallenges")) {
                    out.println("Displaying challenges...");
                    MathChallengeCLI.showMainMenu(connection, username, schoolRegNo);
                    break;
                } else {
                    out.println("Invalid command. Please try again.");
                }
            }
            return "Participant login successful";
        } else {
            return "Invalid username or school registration number.";
        }
    }

    private static boolean verifyLogin(Connection connection, String username, String schoolRegNo) throws SQLException {
        String query = "SELECT * FROM `math-challengez`.acceptedparticipants WHERE username = ? AND schoolRegNo = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, schoolRegNo);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isValid = resultSet.next();
            resultSet.close();
            return isValid;
        }
    }
}
