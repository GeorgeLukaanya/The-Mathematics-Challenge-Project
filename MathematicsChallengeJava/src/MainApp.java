import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MainApp {
    //this is the dummy class i used 
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Math Challenge Application");

        while (true) {
            System.out.println("MENU:");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (login(scanner)) {
                        List<Challenge> challenges = fetchChallengesFromDatabase();
                        if (challenges == null) {
                            System.out.println("Failed to fetch challenges from the database.");
                            return;
                        }
                        ViewChallenges viewChallenges = new ViewChallenges(challenges);
                        handleChallengesMenu(viewChallenges, scanner);
                    } else {
                        System.out.println("Invalid login credentials. Please try again.");
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //Login validation
    /*private static boolean login(Scanner scanner) throws ClassNotFoundException {
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        // Perform database validation
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/math_challenge", "root", ""); // Update with your credentials
            String sql = "SELECT * FROM participants WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                loginSuccess = true;
                System.out.println("Login successful. Welcome, " + username + "!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return loginSuccess;
    }*/

    private static boolean login(Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    private static void handleChallengesMenu(ViewChallenges viewChallenges, Scanner scanner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleChallengesMenu'");
    }

    private static List<Challenge> fetchChallengesFromDatabase() throws ClassNotFoundException {
        List<Challenge> challenges = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/math_challenge", "root", ""); //  credentials
            stmt = conn.createStatement();
            String sql = "SELECT * FROM challenges";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date openDate = rs.getDate("open_date");
                Date closeDate = rs.getDate("close_date");
                int duration = rs.getInt("duration");
                int questionCount = rs.getInt("question_count");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");

                List<Question> questions = fetchQuestionsForChallenge(conn, id);
                challenges.add(new Challenge(id, name, openDate, closeDate, duration, questionCount, createdAt, updatedAt, questions));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return challenges;
    }

    //abraham 
    private static List<Question> fetchQuestionsForChallenge(Connection conn, long challengeId) {
        List<Question> questions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        
        //Abaraham should try placing his code here 
        /*try {
            String sql = "SELECT * FROM questions WHERE challenge_id = ? ORDER BY RAND() LIMIT 10";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, challengeId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String questionText = rs.getString("question_text");
                String correctAnswer = rs.getString("correct_answer");
                int marks = rs.getInt("marks");

                List<Answer> answers = fetchAnswersForQuestion(conn, id);
                questions.add(new Question(id, questionText, correctAnswer, marks, answers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return questions;
    }

    private static List<Answer> fetchAnswersForQuestion(Connection conn, long questionId) {
        List<Answer> answers = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM answers WHERE question_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, questionId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String answerText = rs.getString("answer_text");
                boolean isCorrect = rs.getBoolean("is_correct");

                answers.add(new Answer(id, answerText, isCorrect));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return answers;
    }
    */

    private static handleChallengesMenu(ViewChallenges viewChallenges, Scanner scanner) {
        while (true) {
            System.out.println("MENU:");
            System.out.println("1. View Challenges");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewChallenges.displayChallenges();
                    System.out.print("Enter Challenge ID to attempt: ");
                    long challengeId = scanner.nextLong();
                    Challenge selectedChallenge = viewChallenges.selectChallenge(challengeId);
                    if (selectedChallenge != null) {
                        selectedChallenge.attemptChallenge(scanner);
                    } else {
                        System.out.println("Invalid Challenge ID or Challenge is not active.");
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        return handleChallengesMenu(viewChallenges, null);
    }
}
}