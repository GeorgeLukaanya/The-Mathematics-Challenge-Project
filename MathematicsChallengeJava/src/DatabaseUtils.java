import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtils {

    public static boolean isPreviouslyRejected(Connection conn, String username, String schoolRegNo) {
        String query = "SELECT * FROM RejectedParticipants WHERE username = ? AND schoolRegNo = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, schoolRegNo);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void insertParticipant(Connection conn, String[] applicantDetails) {
        String query = "INSERT INTO AcceptedParticipants (username, schoolRegNo, email, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, applicantDetails[0]);
            pstmt.setString(2, applicantDetails[5]);
            pstmt.setString(3, applicantDetails[3]);
            pstmt.setString(4, applicantDetails[1]);
            pstmt.setString(5, applicantDetails[2]);
            pstmt.setDate(6, java.sql.Date.valueOf(applicantDetails[4]));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertRejected(Connection conn, String username, String schoolRegNo, String reason) {
        String query = "INSERT INTO RejectedParticipants (username, schoolRegNo, reason) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, schoolRegNo);
            pstmt.setString(3, reason);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
