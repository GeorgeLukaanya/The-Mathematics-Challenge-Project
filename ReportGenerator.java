package ChallengeSystem;
import java.util.*;
import java.io.*;

public class ReportGenerator {

    public static void main(String[] args) {
        List<Participant> participants = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of participants:");
        int numParticipants = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < numParticipants; i++) {
            System.out.println("Enter the details for participant " + (i + 1) + ":");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Email: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();
            System.out.print("School Registration Number: ");
            String schoolRegistrationNumber = scanner.nextLine();
            System.out.print("Confirmed (true/false): ");
            boolean confirmed = scanner.nextBoolean();
            System.out.print("Score: ");
            int score = scanner.nextInt();
            scanner.nextLine();

            participants.add(new Participant(username, firstName, lastName, emailAddress, dateOfBirth, schoolRegistrationNumber, confirmed, score));
        }

        generateIndividualReports(participants);
        generateSchoolRankingReport(participants);
        generateAnalyticsReport(participants);
    }

    public static void generateIndividualReports(List<Participant> participants) {
        for (Participant participant : participants) {
            try {
                FileWriter writer = new FileWriter(participant.getUsername() + "_report.txt");
                writer.write("Participant Report\n");
                writer.write("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");
                writer.write("Name: " + participant.getFirstName() + " " + participant.getLastName() + "\n");
                writer.write("Email: " + participant.getEmailAddress() + "\n");
                writer.write("School: " + participant.getSchoolRegistrationNumber() + "\n");
                writer.write("Score: " + participant.getScore() + "\n");
                writer.close();
                System.out.println("A report has beeen generated for " + participant.getUsername());
            } catch (IOException e) {
                System.out.println("An error has occurred while generating report for " + participant.getUsername());
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
            System.out.println("School ranking report has beenn generated.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating school ranking report.");
            e.printStackTrace();
        }
    }

    public static void generateAnalyticsReport(List<Participant> participants) {
        System.out.println("Analytics Report");
        System.out.println("================");
        System.out.println("Total Participants: " + participants.size());
        System.out.println("Highest Score: " + participants.stream().mapToInt(Participant::getScore).max().orElse(0));
        System.out.println("Lowest Score: " + participants.stream().mapToInt(Participant::getScore).min().orElse(0));
        System.out.println("Average Score: " + participants.stream().mapToInt(Participant::getScore).average().orElse(0.0));

        Map<String, List<Participant>> schoolParticipants = new HashMap<>();

        for (Participant participant : participants) {
            schoolParticipants.computeIfAbsent(participant.getSchoolRegistrationNumber(), k -> new ArrayList<>()).add(participant);
        }

        String bestSchool = schoolParticipants.entrySet().stream()
                .max(Comparator.comparingDouble(e -> getAverageScore(e.getValue())))
                .map(Map.Entry::getKey)
                .orElse("N/A");

        String worstSchool = schoolParticipants.entrySet().stream()
                .min(Comparator.comparingDouble(e -> getAverageScore(e.getValue())))
                .map(Map.Entry::getKey)
                .orElse("N/A");

        System.out.println("Best Performing School: " + bestSchool);
        System.out.println("Worst Performing School: " + worstSchool);
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
