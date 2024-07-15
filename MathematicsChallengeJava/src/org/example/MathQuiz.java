package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MathQuiz {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DatabaseConnector.getConnection();
            fetchQuestionsFromDatabase(conn);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("No questions found or unable to fetch questions.");
        } finally {
            DatabaseConnector.closeConnection(conn);
        }
    }

    private static void fetchQuestionsFromDatabase(Connection conn) {
        String query = "SELECT id, question_text FROM questions";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String question = rs.getString("question_text");
               // String answer = rs.getString("answers");

                System.out.println("ID: " + id);
                System.out.println("Question: " + question);
                //System.out.println("Answer: " + answer);
              //  System.out.println("-------------");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching questions: " + e.getMessage());
        }
    }
}
