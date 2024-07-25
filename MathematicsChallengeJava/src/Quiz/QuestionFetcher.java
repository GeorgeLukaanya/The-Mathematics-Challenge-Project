package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionFetcher {
    private Connection connection;

    public QuestionFetcher(Connection connection) {
        this.connection = connection;
    }

    public List<Question> fetchRandomQuestions(int limit) throws SQLException {
        List<Question> questions = new ArrayList<>();

        String query = "SELECT q.id, q.question_text, a.answer_text, a.marks " +
                "FROM questions q " +
                "JOIN answers a ON q.id = a.id " +
                "ORDER BY RAND() LIMIT ?";

        // Use try-with-resources to ensure the statement and result set are closed automatically
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String questionText = rs.getString("question_text");
                    String correctAnswer = rs.getString("answer_text");
                    int marks = rs.getInt("marks");
                    questions.add(new Question(questionText, correctAnswer, marks));
                }
            }
        }

        Collections.shuffle(questions); // Optional: Randomize the order of questions
        return questions;
    }
}
