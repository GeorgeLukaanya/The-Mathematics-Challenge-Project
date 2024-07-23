package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionFetcher {
    private Connection connection;

    public QuestionFetcher(Connection connection) {
        this.connection = connection;
    }

    public List<Question> fetchRandomQuestions(int count) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT q.id, q.question_text, a.answer_text " +
                "FROM questions q " +
                "JOIN answers a ON q.id = a.id " +
                "ORDER BY RAND() LIMIT ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, count);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String questionText = rs.getString("question_text");
                    String answer = rs.getString("answer_text");
                    questions.add(new Question(id, questionText, answer));
                }
            }
        }
        return questions;
    }
}
