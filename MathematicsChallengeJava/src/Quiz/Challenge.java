package Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Challenge {
    private int id;
    private String name;
    private String openingDate;
    private String closingDate;
    private int duration; // in minutes
    private int numberOfQuestions;

    public Challenge(int id, String name, String openingDate, String closingDate, int duration, int numberOfQuestions) {
        this.id = id;
        this.name = name;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.duration = duration;
        this.numberOfQuestions = numberOfQuestions;
    }

    // Getter methods...

    public static List<Challenge> fetchChallenges(Connection connection) throws SQLException {
        List<Challenge> challenges = new ArrayList<>();
        String query = "SELECT id, name, open_date, close_date, duration, question_count FROM challenges";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String openingDate = rs.getString("opening_date");
                    String closingDate = rs.getString("closing_date");
                    int duration = rs.getInt("duration");
                    int numberOfQuestions = rs.getInt("total_questions");
                    challenges.add(new Challenge(id, name, openingDate, closingDate, duration, numberOfQuestions));
                }
            }
        }
        return challenges;
    }
}