import java.sql.*;
import java.util.*;
import java.io.*;

public class ReportGenerator {

    public static void main(String[] args) throws ClassNotFoundException {
        List<Participant> participants = fetchParticipantsFromDatabase();

        if (participants == null) {
            System.out.println("Failed to fetch participants from the database.");
            return;
        }

        generateIndividualReports(participants);
        generateSchoolRankingReport(participants);
        generateAnalyticsReport(participants);
    }

    public static List<Participant> fetchParticipantsFromDatabase() throws ClassNotFoundException {
        List<Participant> participants = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/math_challenge", "username", "");//credentials
            stmt = conn.createStatement();

            // Fetch participants
            String sql = "SELECT * FROM participants";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String username = rs.getString("username");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String emailAddress = rs.getString("email_address");
                String dateOfBirth = rs.getString("date_of_birth");
                String schoolRegistrationNumber = rs.getString("school_registration_number");
                boolean confirmed = rs.getBoolean("confirmed");
                int score = rs.getInt("score");

                participants.add(new Participant(username, firstName, lastName, emailAddress, dateOfBirth, schoolRegistrationNumber, confirmed, score));
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

        return participants;
    }

    public static void generateIndividualReports(List<Participant> participants) {
        for (Participant participant : participants) {
            try {
                FileWriter writer = new FileWriter(participant.getUsername() + "_report.txt");
                writer.write("Participant Report\n");
                writer.write("=================\n");
                writer.write("Name: " + participant.getFirstName() + " " + participant.getLastName() + "\n");
                writer.write("Email: " + participant.getEmailAddress() + "\n");
                writer.write("School: " + participant.getSchoolRegistrationNumber() + "\n");
                writer.write("Score: " + participant.getScore() + "\n");
                writer.close();
                System.out.println("Report generated for " + participant.getUsername());
            } catch (IOException e) {
                System.out.println("An error occurred while generating report for " + participant.getUsername());
                e.printStackTrace();
            }
        }
    }

    public static void generateSchoolRankingReport(List<Participant> participants) {
        Map<String, List<Participant>> schoolParticipants = new HashMap<>();

        for (Participant participant : participants) {
            schoolParticipants.computeIfAbsent(participant.getSchoolRegistrationNumber(), k -> new ArrayList<>()).add(participant);
        }

        try {
            FileWriter writer = new FileWriter("school_ranking_report.txt");
            writer.write("School Ranking Report\n");
            writer.write("=====================\n");

            schoolParticipants.entrySet().stream()
                    .sorted((e1, e2) -> Double.compare(getAverageScore(e2.getValue()), getAverageScore(e1.getValue())))
                    .forEach(entry -> {
                        try {
                            writer.write("School: " + entry.getKey() + "\n");
                            writer.write("Average Score: " + getAverageScore(entry.getValue()) + "\n\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            writer.close();
            System.out.println("School ranking report generated.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating school ranking report.");
            e.printStackTrace();
        }
    }

    public static void generateAnalyticsReport(List<Participant> participants) {
        System.out.println("Analytics Report");
        System.out.println("================");
        System.out.println("Total Participants: " + participants.size());
    }

    private static double getAverageScore(List<Participant> participants) {
        return participants.stream().mapToInt(Participant::getScore).average().orElse(0.0);
    }
}

class Participant {
    private String username;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String dateOfBirth;
    private String schoolRegistrationNumber;
    private boolean confirmed;
    private int score;

    public Participant(String username, String firstName, String lastName, String emailAddress, String dateOfBirth, String schoolRegistrationNumber, boolean confirmed, int score) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.schoolRegistrationNumber = schoolRegistrationNumber;
        this.confirmed = confirmed;
        this.score = score;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSchoolRegistrationNumber() {
        return schoolRegistrationNumber;
    }

    public void setSchoolRegistrationNumber(String schoolRegistrationNumber) {
        this.schoolRegistrationNumber = schoolRegistrationNumber;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
