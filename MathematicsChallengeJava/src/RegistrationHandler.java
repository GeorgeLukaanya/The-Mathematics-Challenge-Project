import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class RegistrationHandler {

    private String smtpHost;
    private String smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private String filePath;

    public RegistrationHandler(String smtpHost, String smtpPort, String smtpUsername, String smtpPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpUsername = smtpUsername;
        this.smtpPassword = smtpPassword;
        this.filePath = new File("participant_details.txt").getAbsolutePath(); // Initialize filePath
    }

    public String getFilePath() {
        return filePath;
    }

    // Method to process the registration command
    public String processRegistrationCommand(String command, Connection conn) {
        String[] details = command.split(" ");
        if (details.length != 8) {
            return "Invalid number of details. Usage: Register <username> <firstname> <lastname> <emailAddress> <date_of_birth> <school_registration_number> <image_file>";
        }

        String userName = details[1];
        String firstName = details[2];
        String lastName = details[3];
        String emailAddress = details[4];
        String dateOfBirth = details[5];
        String registrationNumber = details[6];
        String imageFilePath = details[7];

        System.out.println("Received details from client:");
        System.out.println("Username: " + userName);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email Address: " + emailAddress);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Image File Path: " + imageFilePath);

        // Check the registration number in the database
        String query = "SELECT email FROM schools WHERE reg_no = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, registrationNumber);
            ResultSet rs = pstmt.executeQuery();

            String representativeEmail = null;
            if (rs.next()) {
                representativeEmail = rs.getString("email");
                return sendEmailAndSaveDetails(userName, firstName, lastName, emailAddress, dateOfBirth, registrationNumber, imageFilePath, representativeEmail);
            } else {
                return "No representative email found for the given registration number.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error processing registration.";
        }
    }

    // Method to save participant details to a file, send email, and return result
    private String sendEmailAndSaveDetails(String userName, String firstName, String lastName, String emailAddress, String dob, String regNum, String imageFile, String representativeEmail) {
        try {
            int nextNumber = getNextParticipantNumber(); // Get the next available number
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); // Get current date and time
            boolean isFileEmpty = isFileEmpty(filePath); // Check if file is empty

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                if (isFileEmpty) {
                    // Write header if file is empty
                    writer.write(String.format("%-8s\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-25s\t%-40s\t%-19s\n",
                            "Number", "Username", "First Name", "Last Name", "Email", "Date of Birth", "Registration No.", "Image File", "Date and Time"));
                }

                // Write participant details in a tabular format
                writer.write(String.format("%-8d\t%-20s\t%-20s\t%-20s\t%-20s\t%-20s\t%-25s\t%-40s\t%-19s\n",
                        nextNumber, userName, firstName, lastName, emailAddress, dob, regNum, imageFile, dateTime));

                // Send email with a reminder
                sendEmailReminder(representativeEmail);
                System.out.println("Email reminder sent to: " + representativeEmail);
                return "Participant details saved to: " + filePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving participant details.";
        }
    }

    // Method to check if the file is empty
    private boolean isFileEmpty(String filePath) {
        File file = new File(filePath);
        return file.length() == 0;
    }

    // Method to get the next available participant number
    private int getNextParticipantNumber() {
        int maxNumber = 0;
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split("\t");
                    try {
                        int number = Integer.parseInt(parts[0].trim());
                        if (number > maxNumber) {
                            maxNumber = number;
                        }
                    } catch (NumberFormatException e) {
                        // Ignore lines that don't start with a number
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maxNumber + 1;
    }

    // Method to send email reminder
    private void sendEmailReminder(String recipientEmail) {
        // Set properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create session
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@example.com")); // Change as needed
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Reminder to verify student");
            message.setText("Please verify the student details.");

            // Send message
            Transport.send(message);

            System.out.println("Reminder email sent successfully to " + recipientEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
