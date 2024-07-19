package Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MathChallengeCLI {
    private static Connection connection;

    public static void main(String[] args) {
        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/math-challengez";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);

            // Start the main menu
            showMainMenu();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("=========");
            System.out.println("1. View Challenges");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showChallengesMenu(scanner);
                    break;
                case "2":
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showChallengesMenu(Scanner scanner) {
        System.out.println("\nChallenges Menu");
        System.out.println("================");
        // Here you can add more challenges or fetch them from the database
        System.out.println("1. Mathematics Challenge");
        System.out.print("Choose a challenge: ");

        String choice = scanner.nextLine().trim();
        if (choice.equals("1")) {
            System.out.println("Type 'attemptChallenge' to start the Mathematics Challenge.");
            while (true) {
                String command = scanner.nextLine().trim().toLowerCase();
                if (command.equals("attemptchallenge")) {
                    attemptMathChallenge(scanner);
                    break;
                } else {
                    System.out.println("Invalid command. Please type 'attemptChallenge' to start.");
                }
            }
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private static void attemptMathChallenge(Scanner scanner) {
        try {
            QuestionFetcher questionFetcher = new QuestionFetcher(connection);
            List<Question> questions = questionFetcher.fetchRandomQuestions(10); // Fetch 10 random questions

            // Display the start message and instructions
            System.out.println("The Mathematics Challenge Quiz");
            System.out.println("==============================");
            System.out.println("Type 'start' to begin the quiz.");

            // Wait for user to start the quiz
            while (true) {
                String command = scanner.nextLine().trim().toLowerCase();
                if (command.equals("start")) {
                    MathQuiz.stopwatch.start();
                    break;
                }
            }

            // Start the quiz
            MathQuiz.startQuiz(questions, scanner);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
