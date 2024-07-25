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

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String questionText = resultSet.getString("question_text");
                    String correctAnswer = resultSet.getString("answer_text"); // Assuming this is correct
                    int marks = resultSet.getInt("marks");

                    questions.add(new Question(questionText, correctAnswer, marks));
                }
            }
        }

        Collections.shuffle(questions); // Optional: Randomize the order of questions
        return questions;
    }
}
