import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private int id;
    private String question;
    private String answer;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

public class MathQuiz {

    public static void main(String[] args) {
        List<Question> questions = fetchQuestionsFromDatabase();
        if (questions != null && !questions.isEmpty()) {
            runQuiz(questions);
        } else {
            System.out.println("No questions found or unable to fetch questions.");
        }
    }

    private static List<Question> fetchQuestionsFromDatabase() {
        List<Question> questions = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, question, answer FROM questions");

            while (rs.next()) {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setQuestion(rs.getString("question"));
                question.setAnswer(rs.getString("answer"));
                questions.add(question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnector.closeConnection(conn);
        }
        return questions;
    }

    private static void runQuiz(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println("Question: " + question.getQuestion());
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine();

            if (userAnswer.equalsIgnoreCase(question.getAnswer())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + question.getAnswer());
            }
        }

        System.out.println("You scored " + score + " out of " + questions.size());
    }
}
