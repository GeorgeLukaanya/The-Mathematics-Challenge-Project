package Quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionFetcher {
    private Connection connection;

    public QuestionFetcher(Connection connection) {
        this.connection = connection;
    }

    public List<Question> fetchRandomQuestions(int count) throws SQLException {
        List<Question> questions = new ArrayList<>();
<<<<<<< HEAD
        Statement questionStatement = null;
        ResultSet questionResultSet = null;
        Statement answerStatement = null;
        ResultSet answerResultSet = null;

        try {
            questionStatement = connection.createStatement();

            // Fetch random question IDs
            String questionQuery = "SELECT id, question_text FROM questions ORDER BY RAND() LIMIT " + count;
            questionResultSet = questionStatement.executeQuery(questionQuery);

            while (questionResultSet.next()) {
                int questionId = questionResultSet.getInt("id");
                String questionText = questionResultSet.getString("question_text");

                // Use a new Statement for the answer query
                answerStatement = connection.createStatement();
                String answerQuery = "SELECT answer_text, marks FROM answers WHERE id = " + questionId + " LIMIT 1";
                answerResultSet = answerStatement.executeQuery(answerQuery);

                if (answerResultSet.next()) {
                    String correctAnswer = answerResultSet.getString("answer_text");
                    int marks = answerResultSet.getInt("marks");
                    questions.add(new Question(questionText, correctAnswer, marks));
                }

                // Close the answerResultSet and answerStatement to avoid issues
                if (answerResultSet != null && !answerResultSet.isClosed()) {
                    answerResultSet.close();
                }
                if (answerStatement != null && !answerStatement.isClosed()) {
                    answerStatement.close();
=======
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
>>>>>>> 3aa540f69e87791a72b42364ef23fbc0f48ed0f9
                }
            }
        } finally {
            // Close all resources
            if (questionResultSet != null && !questionResultSet.isClosed()) {
                questionResultSet.close();
            }
            if (questionStatement != null && !questionStatement.isClosed()) {
                questionStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }

        return questions;
    }
}
