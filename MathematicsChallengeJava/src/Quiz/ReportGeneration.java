package Quiz;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGeneration {
    public static void generateReport(String username, String schoolRegNo, long totalTimeTaken, List<QuestionAttempt> questionAttempts) {
        try (FileWriter writer = new FileWriter("Quiz_Report.txt")) {
            writer.write("Mathematics Challenge Report\n");
            writer.write("============================\n\n");
            writer.write("Participant: " + username + "\n");
            writer.write("School Registration Number: " + schoolRegNo + "\n");
            writer.write("Total Time Taken: " + (totalTimeTaken / 1000) + " seconds\n\n");

            // Summary
            int totalScore = questionAttempts.stream().mapToInt(QuestionAttempt::getMarksAwarded).sum();
            long totalQuestions = questionAttempts.size();
            long correctAnswers = questionAttempts.stream().filter(QuestionAttempt::isCorrect).count();
            double accuracyRate = (double) correctAnswers / totalQuestions * 100;
            double percentageScore = (double) totalScore / (totalQuestions * 6) * 100; // Assuming each question has a maximum score of 6

            writer.write("Summary\n");
            writer.write("=======\n");
            writer.write("Total Score: " + totalScore + "\n");
            writer.write("Percentage Score: " + String.format("%.2f", percentageScore) + "%\n");
            writer.write("Accuracy Rate: " + String.format("%.2f", accuracyRate) + "%\n\n");

            // Detailed Report
            writer.write("Detailed Report\n");
            writer.write("===============\n");
            for (QuestionAttempt attempt : questionAttempts) {
                writer.write("Question: " + attempt.getQuestionText() + "\n");
                writer.write("Your Answer: " + attempt.getUserAnswer() + "\n");
                writer.write("Correct: " + (attempt.isCorrect() ? "Yes" : "No") + "\n");
                writer.write("Marks Awarded: " + attempt.getMarksAwarded() + "\n\n");
            }
            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
