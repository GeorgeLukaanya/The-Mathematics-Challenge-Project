package Quiz;

import java.util.List;
import java.util.Scanner;

    public class MathQuiz {
        private static boolean quizOver = false;
        private static Stopwatch stopwatch = new Stopwatch(5); // Set the timer for 5 minutes

        public static void main(String[] args) {
            List<Question> questions = QuestionFetcher.fetchRandomQuestions();
            Scanner scanner = new Scanner(System.in);

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
            for (int i = 0; i < questions.size() && !quizOver; i++) {
                Question question = questions.get(i);

                // Clear the screen and display the remaining time at the top
                System.out.print("\033[H\033[2J"); // Clear the screen
                System.out.flush();

                // Display the remaining time in green
                System.out.println("\033[1;32mTime Remaining: " + stopwatch.formatTime(stopwatch.remainingTime()) + "\033[0m");
                System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
                System.out.print("Your answer: ");
                String userAnswer = scanner.nextLine();
                // Store or process the user's answer

                // Check if time is up
                if (stopwatch.remainingTime() <= 0) {
                    quizOver = true;
                    System.out.println("\nTime's up!");
                }
            }

            // Stop the stopwatch
            stopwatch.stop();
            System.out.println("\nQuiz completed!");
            System.out.println("Final time remaining: " + stopwatch.formatTime(stopwatch.remainingTime()));

            scanner.close();
        }
    }

