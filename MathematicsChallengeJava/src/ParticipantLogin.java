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

            while (true) {
                System.out.println("Enter command: Login <username> <schoolRegNo>");
                String input = scanner.nextLine();
                String[] commandParts = input.split(" ");

                if (commandParts.length == 3 && commandParts[0].equalsIgnoreCase("Login")) {
                    String username = commandParts[1];
                    String schoolRegNo = commandParts[2];

                    if (verifyLogin(connection, username, schoolRegNo)) {
                        System.out.println("Login successful!");

                        while (true) {
                            System.out.println("viewchallenges");
                            System.out.println("Enter command: viewchallenges ");
                            String viewCommand = scanner.nextLine();
                            if (viewCommand.equalsIgnoreCase("viewchallenges")) {
                                System.out.println("Displaying challenges...");

                                // Add code to display challenges


                                break;
                            } else {
                                System.out.println("Invalid command. Please try again.");
                            }
                        }
                        break;
                    } else {
                        System.out.println("Invalid username or school registration number. Please try again.");
                    }
                } else {
                    System.out.println("Invalid command format. Use: Login <username> <schoolRegNo>");
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static boolean verifyLogin(Connection connection, String username, String schoolRegNo) throws SQLException {
        String query = "SELECT * FROM `math-challengez`.acceptedparticipants WHERE username = ? AND schoolRegNo = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, schoolRegNo);

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean isValid = resultSet.next();

        resultSet.close();
        preparedStatement.close();

        return isValid;
    }
}

