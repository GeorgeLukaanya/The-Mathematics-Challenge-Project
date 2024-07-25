package Quiz;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Scanner;

public class MathQuiz {
    private static Instant startTime;
    private static Instant endTime;

    public static void startStopwatch() {
        startTime = Instant.now();
    }

    public static void stopStopwatch() {
        endTime = Instant.now();
    }

    public static long getElapsedTime() {
        return Duration.between(startTime, endTime).toMillis();
    }

    public static int startQuiz(List<Question> questions, Scanner scanner) {
        int marks = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String answer = scanner.nextLine().trim();

            boolean isCorrect = answer.equals(question.getCorrectAnswer());

            if (answer.equals("-") || answer.isEmpty()) {
                // Participant is unsure or pressed enter without an answer
                marks += 0;
            } else if (!isCorrect) {
                // Wrong answer
                marks -= 3;
            } else {
                // Correct answer
                marks += 6;
            }
        }
        return marks;
    }
}
