package Quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathChallengeCLI {
    private static Connection connection;
    private static String currentUsername;
    private static String currentSchoolRegNo;

    // ANSI escape codes for coloring
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Database connection setup
        String url = "jdbc:mysql://localhost:3306/math-challengez";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);

            // Start the main menu
            showMainMenu(connection, "defaultUser", "defaultSchoolRegNo");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showMainMenu(Connection conn, String username, String schoolRegNo) {
        connection = conn;
        currentUsername = username;
        currentSchoolRegNo = schoolRegNo;

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
        System.out.println("1. Abacus");
        System.out.println("2. Algebra");
        System.out.println("3. Calculus");
        System.out.print("Choose a challenge: ");

        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1":
            case "2":
            case "3":
                System.out.println("Type 'attemptChallenge' to start the challenge.");
                while (true) {
                    String command = scanner.nextLine().trim().toLowerCase();
                    if (command.equals("attemptchallenge")) {
                        attemptMathChallenge(scanner);
                        break;
                    } else {
                        System.out.println("Invalid command. Please type 'attemptChallenge' to start.");
                    }
                }
                break;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private static void attemptMathChallenge(Scanner scanner) {
        try {
            QuestionFetcher questionFetcher = new QuestionFetcher(connection);
            int attempts = 0;
            final int maxAttempts = 3;
            final int totalQuestions = 10;

            while (attempts < maxAttempts) {
                attempts++;
                System.out.println("Attempt " + attempts + " of " + maxAttempts);

                List<Question> questions = questionFetcher.fetchRandomQuestions(totalQuestions); // Fetch 10 random questions
                List<QuestionAttempt> questionAttempts = new ArrayList<>();

                // Display the start message and instructions
                System.out.println("The Mathematics Challenge Quiz");
                System.out.println("==============================");
                System.out.println("Type 'start' to begin the quiz.");

                // Wait for user to start the quiz
                while (true) {
                    String command = scanner.nextLine().trim().toLowerCase();
                    if (command.equals("start")) {
                        MathQuiz.startStopwatch();
                        break;
                    }
                }

                // Start the quiz
                int marks = 0;
                long startTime = System.currentTimeMillis();
                long quizDuration = 5 * 60 * 1000; // 5 minutes in milliseconds
                int questionIndex = 0;

                while (questionIndex < totalQuestions) {
                    long currentTime = System.currentTimeMillis();
                    long timeElapsed = currentTime - startTime;
                    long timeRemaining = quizDuration - timeElapsed;

                    if (timeRemaining <= 0) {
                        System.out.println("Time's up!");
                        break;
                    }

                    Question question = questions.get(questionIndex);
                    displayQuizStatus(timeRemaining, totalQuestions - questionIndex - 1);

                    System.out.println(question.getQuestionText());
                    String answer = scanner.nextLine().trim();

                    boolean isCorrect = answer.equals(question.getCorrectAnswer());

                    if (answer.equals("-") || answer.isEmpty()) {
                        // Participant is unsure or pressed enter without an answer
                        questionAttempts.add(new QuestionAttempt(question.getQuestionText(), answer, false, 0));
                    } else if (!isCorrect) {
                        // Wrong answer
                        marks -= 3;
                        questionAttempts.add(new QuestionAttempt(question.getQuestionText(), answer, false, -3));
                    } else {
                        // Correct answer
                        marks += 6;
                        questionAttempts.add(new QuestionAttempt(question.getQuestionText(), answer, true, 6));
                    }

                    questionIndex++;
                }

                MathQuiz.stopStopwatch();
                long totalTimeTaken = MathQuiz.getElapsedTime();

                // Generate the report
                ReportGeneration.generateReport(currentUsername, currentSchoolRegNo, totalTimeTaken, questionAttempts);

                if (attempts < maxAttempts) {
                    System.out.println("Do you want to attempt the challenge again? (yes/no)");
                    String retry = scanner.nextLine().trim().toLowerCase();
                    if (!retry.equals("yes")) {
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    }
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("You have used all your attempts. Exiting...");
                scanner.close();
                System.exit(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayQuizStatus(long timeRemaining, int questionsLeft) {
        long minutes = (timeRemaining / 1000) / 60;
        long seconds = (timeRemaining / 1000) % 60;

        System.out.printf("%sTime Remaining: %02d:%02d%s | %sQuestions Remaining: %d%s\n", ANSI_RED, minutes, seconds, ANSI_RESET, ANSI_BLUE, questionsLeft, ANSI_RESET);
    }
}
