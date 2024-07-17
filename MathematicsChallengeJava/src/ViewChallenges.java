import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ViewChallenges {
    private List<Challenge> challenges;

    public ViewChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void displayChallenges() {
        System.out.println("Available Challenges:");
        for (Challenge challenge : challenges) {
            System.out.println("Challenge ID: " + challenge.getId());
            System.out.println("Name: " + challenge.getName());
            System.out.println("Open Date: " + challenge.getOpenDate());
            System.out.println("Close Date: " + challenge.getCloseDate());
            System.out.println("Duration: " + challenge.getDuration());
            System.out.println("Question Count: " + challenge.getQuestionCount());
            System.out.println("Created At: " + challenge.getCreatedAt());
            System.out.println("Updated At: " + challenge.getUpdatedAt());
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        }
    }

    public Challenge selectChallenge(long id) {
        for (Challenge challenge : challenges) {
            if (challenge.getId() == id) {
                return challenge;
            }
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<Challenge> challenges = fetchChallengesFromDatabase();

        if (challenges == null) {
            System.out.println("Failed to fetch challenges from the database.");
            return;
        }

        ViewChallenges viewChallenges = new ViewChallenges(challenges);
        Scanner scanner = new Scanner(System.in);

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
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static List<Challenge> fetchChallengesFromDatabase() throws ClassNotFoundException {
        List<Challenge> challenges = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/math_challenge", "root", ""); // crededntials
            stmt = conn.createStatement();

            // Fetch challenges
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

    private static List<Question> fetchQuestionsForChallenge(Connection conn, long challengeId) {
        List<Question> questions = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
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
}

class Challenge {
    private int id;
    private String name;
    private Date openDate;
    private Date closeDate;
    private int duration;
    private int questionCount;
    private String createdAt;
    private String updatedAt;
    private List<Question> questions;

    public Challenge(int id, String name, Date openDate, Date closeDate, int duration, int questionCount, String createdAt, String updatedAt, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.closeDate = closeDate;
        this.duration = duration;
        this.questionCount = questionCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public int getDuration() {
        return duration;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void attemptChallenge(Scanner scanner) {
        int totalMarks = 0;
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            List<Answer> answers = question.getAnswers();
            for (Answer answer : answers) {
                System.out.println("- " + answer.getAnswerText());
            }
            System.out.print("Your answer: ");
            String answerText = scanner.next();
            Answer selectedAnswer = answers.stream().filter(a -> a.getAnswerText().equalsIgnoreCase(answerText)).findFirst().orElse(null);
            if (selectedAnswer != null && selectedAnswer.isCorrect()) {
                totalMarks += question.getMarks();
                System.out.println("Correct! You earned " + question.getMarks() + " marks.");
            } else {
                System.out.println("Incorrect! The correct answer is " + question.getCorrectAnswer() + ".");
            }
        }
        System.out.println("You scored " + totalMarks + " out of " + (questions.size() * 10) + " marks.");
    }
}

class Question {
    private int id;
    private String questionText;
    private String correctAnswer;
    private int marks;
    private List<Answer> answers;

    public Question(int id, String questionText, String correctAnswer, int marks, List<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.marks = marks;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getMarks() {
        return marks;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}

class Answer {
    private int id;
    private String answerText;
    private boolean isCorrect;

    public Answer(int id, String answerText, boolean isCorrect) {
        this.id = id;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
