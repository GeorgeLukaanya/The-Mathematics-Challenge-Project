import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

        
        if (isValidLogin(username, password)) {
            // if at all our login is successful, then we display challenges
            List<Challenge> challenges = fetchChallengesFromDatabase();
            ViewChallenges viewChallenges = new ViewChallenges(challenges);
            viewChallenges.displayChallenges();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Challenge ID to attempt: ");
            long challengeId = scanner.nextLong();
            Challenge selectedChallenge = viewChallenges.selectChallenge(challengeId);
            if (selectedChallenge != null) {
                //from here I think Abraham can check out if at all his code can be covered from the attempt challenge method
                selectedChallenge.attemptChallenge(scanner);
            } else {
                System.out.println("Invalid Challenge ID or Challenge is not active.");
            }
        } else {
            System.out.println("Invalid username or password.");
        }
        return isValid;
    }

    private static boolean isValidLogin(String username, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValidLogin'");
    }

    private static List<Challenge> fetchChallengesFromDatabase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchChallengesFromDatabase'");
    }
}

