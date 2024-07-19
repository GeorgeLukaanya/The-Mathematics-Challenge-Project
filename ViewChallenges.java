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
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/math-challengez", "username", "");//credentials
            stmt = conn.createStatement();
            // Fetch challenges
            String sql = "SELECT * FROM challenges";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date open_date = rs.getDate("open_date");
                Date close_date = rs.getDate("close_date");
                int duration = rs.getInt("duration");
                int question_count = rs.getInt("question_count");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");

                List<Question> questions = fetchQuestionsForChallenge(conn, id);
                challenges.add(new Challenge(id, name, open_date, close_date, duration, question_count, created_at, updated_at, questions));
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
             //rand() helps to randomize 10 questions
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, challengeId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String question_text = rs.getString("question_text");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");

                List<Answer> answers = fetchAnswersForQuestion(conn, id);
                questions.add(new Question(id, question_text, created_at, updated_at, answers));
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
                String answer_text = rs.getString("answer_text");
                int marks = rs.getInt("marks");
                String created_at = rs.getString("created_at");
                String updated_at = rs.getString("updated_at");
                answers.add(new Answer(id, answer_text, marks, created_at, updated_at));
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
    private Date open_date;
    private Date close_date;
    private int duration;
    private int question_count;
    private String created_at;
    private String updated_at;
    private List<Question> questions;

    public Challenge(int id, String name, Date open_date, Date close_date, int duration, int question_count, String created_at, String updated_at, List<Question> questions) {
        this.id = id;
        this.name = name;
        this.open_date = open_date;
        this.close_date = close_date;
        this.duration = duration;
        this.question_count = question_count;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getOpenDate() {
        return open_date;
    }

    public Date getCloseDate() {
        return close_date;
    }

    public int getDuration() {
        return duration;
    }

    public int getQuestionCount() {
        return question_count;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
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
            if (selectedAnswer != null && selectedAnswer.getMarks() > 0) {
                totalMarks += selectedAnswer.getMarks();
                System.out.println("Correct! You earned " + selectedAnswer.getMarks() + " marks.");
            } else {
                System.out.println("Incorrect! The correct answer is " + answers.stream().filter(a -> a.getMarks() > 0).findFirst().get().getAnswerText() + ".");
            }
        }
        System.out.println("You scored " + totalMarks + " out of " + (questions.size() * 10) + " marks.");
    }
}

class Question {
    private int id;
    private String question_text;
    private String created_at;
    private String updated_at;
    private List<Answer> answers;

    public Question(int id, String question_text, String created_at, String updated_at, List<Answer> answers) {
        this.id = id;
        this.question_text = question_text;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.answers = answers;
    }


    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return question_text;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}

class Answer {
    private int id;
    private String answer_text;
    private int marks;
    private String created_at;
    private String updated_at;

    public Answer(int id, String answer_text, int marks, String created_at, String updated_at) {
        this.id = id;
        this.answer_text = answer_text;
        this.marks = marks;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getAnswerText() {
        return answer_text;
    }

    public int getMarks() {
        return marks;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }
}
