import java.io.*;
import java.sql.*;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class RegistrationHandler {

    private String smtpHost;
    private String smtpPort;
    private String smtpUsername;
    private String smtpPassword;

    public RegistrationHandler(String smtpHost, String smtpPort, String smtpUsername, String smtpPassword) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.smtpUsername = smtpUsername;
        this.smtpPassword = smtpPassword;
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
        String query = "SELECT RepresentativeEmail FROM School WHERE SchoolRegNo = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, registrationNumber);
            ResultSet rs = pstmt.executeQuery();

            String representativeEmail = null;
            if (rs.next()) {
                representativeEmail = rs.getString("RepresentativeEmail");
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
        String filePath = "participant_details.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Participant Details:\n");
            writer.write("Username: " + userName + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Email Address: " + emailAddress + "\n");
            writer.write("Date of Birth: " + dob + "\n");
            writer.write("Registration Number: " + regNum + "\n");
            writer.write("Image File Path: " + imageFile + "\n");
            if (representativeEmail != null) {
                writer.write("Representative Email: " + representativeEmail + "\n");
            } else {
                writer.write("Representative Email: Not found\n");
            }
            writer.write("\n");

            // Send email with attachment
            sendEmailWithAttachment(representativeEmail, filePath);
            return "Participant details saved to: " + filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error saving participant details.";
        }
    }

    // Method to send email with attachment
    private void sendEmailWithAttachment(String recipientEmail, String filePath) {
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
            message.setSubject("Participant Registration Details");

            // Create body part for the message
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached participant details.");

            // Create multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attachment part
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(new File(filePath).getName());
            multipart.addBodyPart(messageBodyPart);

            // Set the complete message
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully to " + recipientEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
