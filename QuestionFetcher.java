package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionFetcher {

    public static List<Question> fetchRandomQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT id, question_text FROM questions ORDER BY RAND() LIMIT 10";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String questionText = resultSet.getString("question_text");
                questions.add(new Question(id, questionText, ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
