import java.io.*;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class RepresentativeMenu {
    private Connection connection;
    private ApplicantsFileHandler fileHandler;
    private EmailSender emailSender;
    private static final String PARTICIPANTS_FILE_PATH = "/opt/lampp/htdocs/The-Mathematics-Challenge-Project/MathematicsChallengeJava//participants.txt"; // Adjust the path based on your project structure

    public RepresentativeMenu(Connection connection) {
        this.connection = connection;
        this.fileHandler = new ApplicantsFileHandler(
                "imap.mailtrap.io", // IMAP server for Mailtrap
                "993", // IMAP port for SSL
                "your_mailtrap_username", // Mailtrap username
                "your_mailtrap_password"  // Mailtrap password
        );
        this.emailSender = new EmailSender(
                "sandbox.smtp.mailtrap.io",
                "2525",
                "f3683be4bf7d0e",
                "4bb6532709da20"
        );
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome, Representative. Choose an option:");
            System.out.println("1. View Applicants");
            System.out.println("2. Confirm Applicant (usage: confirm yes/no username)");
            System.out.println("3. Exit");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    viewApplicants();
                    break;
                case "2":
                    System.out.println("Enter command: ");
                    String[] command = scanner.nextLine().split(" ");
                    if (command.length == 3 && command[0].equalsIgnoreCase("confirm")) {
                        confirmApplicant(command[1], command[2]);
                    } else {
                        System.out.println("Invalid command. Usage: confirm yes/no username");
                    }
                    break;
                case "3":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewApplicants() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PARTICIPANTS_FILE_PATH))) {
            String line;
            System.out.println("List of Applicants:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading participants file: " + e.getMessage());
        }
    }

    private void confirmApplicant(String decision, String username) {
        boolean isAccepted = decision.equalsIgnoreCase("yes");
        List<String[]> applicants = fileHandler.fetchApplicantsFromEmails();

        for (String[] applicant : applicants) {
            if (applicant[0].equals(username)) {
                if (isAccepted) {
                    if (DatabaseUtils.isPreviouslyRejected(connection, username, applicant[5])) {
                        System.out.println("Applicant " + username + " has been previously rejected and cannot re-register under the same school.");
                        return;
                    }
                    DatabaseUtils.insertParticipant(connection, applicant);
                    emailSender.sendEmailWithAttachment(applicant[3], "applicant_accepted.txt");
                } else {
                    DatabaseUtils.insertRejected(connection, username, applicant[5], "Rejected by representative.");
                    emailSender.sendEmailWithAttachment(applicant[3], "applicant_rejected.txt");
                }
                fileHandler.removeApplicant(applicant);
                System.out.println("Applicant " + username + " has been " + (isAccepted ? "accepted" : "rejected") + ".");
                return;
            }
        }
        System.out.println("Applicant " + username + " not found.");
    }
}
