package Quiz;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    public static String checkApplicantEmail() {
        String email = "";
        String query = "SELECT email FROM participants WHERE username = '" + MathChallengeCLI.currentUsername + "'";

        try (Connection conn = MathChallengeCLI.connection; Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email;
    }
}
