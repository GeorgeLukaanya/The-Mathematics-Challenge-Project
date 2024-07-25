package Quiz;

import java.util.List;

public class ReportGeneration {

    // ANSI escape codes for coloring
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";

    /**
     * Generates and displays a formatted report of the quiz results.
     *
     * @param username         the username of the participant
     * @param schoolRegNo      the school registration number of the participant
     * @param totalTime        the total time taken for the quiz in milliseconds
     * @param questionAttempts a list of QuestionAttempt objects containing quiz details
     */
    public static void generateReport(String username, String schoolRegNo, long totalTime, List<QuestionAttempt> questionAttempts) {
        // Print report heading
        System.out.println("\n" + ANSI_CYAN + "Challenge Report" + ANSI_RESET);
        System.out.println("=================");

        // Print participant details
        System.out.printf("%-30s: %s\n", "Username", username);
        System.out.printf("%-30s: %s\n", "School Registration Number", schoolRegNo);
        System.out.printf("%-30s: %d seconds\n", "Total Time Taken", totalTime / 1000);
        System.out.println();

        // Define column widths
        int questionWidth = 50;
        int answerWidth = 20;
        int correctAnswerWidth = 20;
        int scoreWidth = 10;
        int timeTakenWidth = 10;

        // Print table headers with proper spacing
        System.out.printf("%-" + questionWidth + "s | %-" + answerWidth + "s | %-" + correctAnswerWidth + "s | %-" + scoreWidth + "s | %-" + timeTakenWidth + "s\n",
                ANSI_CYAN + "Question" + ANSI_RESET,
                ANSI_CYAN + "Your Answer" + ANSI_RESET,
                ANSI_CYAN + "Correct Answer" + ANSI_RESET,
                ANSI_CYAN + "Score" + ANSI_RESET,
                ANSI_CYAN + "Time Taken" + ANSI_RESET);

        // Print a separator line
        System.out.println(new String(new char[questionWidth + answerWidth + correctAnswerWidth + scoreWidth + timeTakenWidth + 13]).replace("\0", "-"));

        // Print each question attempt with adjusted formatting
        for (QuestionAttempt attempt : questionAttempts) {
            System.out.printf("%-" + questionWidth + "s | %-" + answerWidth + "s | %-" + correctAnswerWidth + "s | %-" + scoreWidth + "d | %-" + timeTakenWidth + "d\n",
                    truncate(attempt.getQuestionText(), questionWidth),
                    truncate(attempt.getGivenAnswer(), answerWidth),
                    truncate(attempt.getCorrectAnswer(), correctAnswerWidth),
                    attempt.getScore(),
                    attempt.getTimeTaken() / 1000); // Convert milliseconds to seconds
        }

        // Print ending message
        System.out.println();
        System.out.println(ANSI_CYAN + "Thank you for participating in the Mathematics Challenge!" + ANSI_RESET);
    }

    /**
     * Truncates a string to the specified width.
     *
     * @param str   the string to truncate
     * @param width the width to truncate to
     * @return the truncated string
     */
    private static String truncate(String str, int width) {
        if (str.length() <= width) {
            return str;
        } else {
            return str.substring(0, width - 3) + "...";
        }
    }
}
