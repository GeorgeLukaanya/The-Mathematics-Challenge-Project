package Quiz;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MathChallengeCLI {
    static Connection connection;
    static String currentUsername;
    private static String currentSchoolRegNo;
    private static Stopwatch stopwatch = new Stopwatch();

    // ANSI escape codes for coloring
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        // Setup database connection
        connectToDatabase();

        // Start the main menu
        showMainMenu(connection, currentUsername, currentSchoolRegNo);
    }

    private static void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/math-challengez";
        String user = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void showMainMenu(Connection conn, String username, String schoolRegNo) {
        connection = conn; // Set the static connection field
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
        System.out.println("Type 'attemptChallenge <challengeNumber>' to start the challenge.");

        while (true) {
            String command = scanner.nextLine().trim().toLowerCase();
            if (command.startsWith("attemptchallenge ")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    try {
                        int challengeNumber = Integer.parseInt(parts[1]);
                        attemptMathChallenge(scanner, challengeNumber);
                        break;
                    } catch (NumberFormatException | DocumentException | IOException e) {
                        System.out.println("Invalid challenge number. Please enter a valid number.");
                    }
                } else {
                    System.out.println("Invalid command format. Please use 'attemptChallenge <challengeNumber>'.");
                }
            } else {
                System.out.println("Invalid command. Please use 'attemptChallenge <challengeNumber>'.");
            }
        }
    }

    private static void attemptMathChallenge(Scanner scanner, int challengeNumber) throws DocumentException, IOException {
        try {
            QuestionFetcher questionFetcher = new QuestionFetcher(connection);
            final int maxAttempts = 3;
            final int totalQuestions = 10;
            int attempts = 0;

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
                        stopwatch.start();
                        break;
                    }
                }

                // Start the quiz
                int marks = 0;
                int questionIndex = 0;

                while (questionIndex < totalQuestions) {
                    long questionStartTime = System.currentTimeMillis();
                    int elapsedTime = (int) stopwatch.getElapsedTime(); // Get elapsed time in milliseconds
                    int timeRemaining = 5 * 60 * 1000 - elapsedTime; // 5 minutes in milliseconds

                    if (timeRemaining <= 0) {
                        System.out.println("Time's up!");
                        break;
                    }

                    Question question = questions.get(questionIndex);
                    displayQuizStatus(timeRemaining, totalQuestions - questionIndex - 1);

                    System.out.println(question.getQuestionText());
                    String answer = scanner.nextLine().trim();

                    boolean isCorrect = answer.equals(question.getCorrectAnswer());
                    long questionEndTime = System.currentTimeMillis();
                    long questionTimeTaken = questionEndTime - questionStartTime;

                    if (answer.equals("-") || answer.isEmpty()) {
                        // Participant is unsure or pressed enter without an answer
                        questionAttempts.add(new QuestionAttempt(question.getQuestionText(), answer, question.getCorrectAnswer(), 0, false, questionTimeTaken, currentUsername));
                    } else if (!isCorrect) {
                        // Wrong answer
                        marks -= 3;
                        questionAttempts.add(new QuestionAttempt(question.getQuestionText(), answer, question.getCorrectAnswer(), -3, false, questionTimeTaken, currentUsername));
                    } else {
                        // Correct answer
                        marks += 6;
                        questionAttempts.add(new QuestionAttempt(question.getQuestionText(), answer, question.getCorrectAnswer(), 6, true, questionTimeTaken, currentUsername));
                    }

                    questionIndex++;
                }

                stopwatch.stop();
                long totalTimeTaken = stopwatch.getElapsedTime();

                // Display the report on the server
                ReportGeneration.generateReport(currentUsername, currentSchoolRegNo, totalTimeTaken, questionAttempts);

                // Generate and send PDF report
                String pdfFilePath = "questions_report.pdf"; // Relative path for PDF file in the project folder
                PDFReportSender.generatePDFReport(questions, pdfFilePath);
                PDFReportSender.sendPDFReport("recipient@example.com", "Quiz Report", "Please find attached the quiz report.", pdfFilePath);

                if (attempts < maxAttempts) {
                    System.out.println("Thank you for participating in the Mathematics Challenge!");
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
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void displayQuizStatus(int timeRemaining, int questionsRemaining) {
        int minutes = timeRemaining / 60000;
        int seconds = (timeRemaining % 60000) / 1000;
        System.out.printf(ANSI_YELLOW + "Time Remaining: %02d:%02d " + ANSI_GREEN + "| Questions Remaining: %d" + ANSI_RESET + "\n", minutes, seconds, questionsRemaining);
    }
}
