package Quiz;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Sends an email with an attachment.
 */
public class PDFEmailSender {

    private static final String SMTP_HOST = "smtp.example.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USER = "your-email@example.com";
    private static final String SMTP_PASS = "your-email-password";

    /**
     * Sends an email with an attachment.
     *
     * @param to          Recipient email address.
     * @param subject     Subject of the email.
     * @param body        Body of the email.
     * @param attachment  Path to the attachment file.
     * @throws IOException If there's an issue with the attachment file.
     */
    public static void sendEmailWithAttachment(String to, String subject, String body, String attachment) throws IOException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USER, SMTP_PASS);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            // Add email body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            multipart.addBodyPart(messageBodyPart);

            // Add attachment
            messageBodyPart = new MimeBodyPart();
            File file = new File(attachment);
            ((MimeBodyPart) messageBodyPart).attachFile(file);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Email sent successfully!");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
            throw new IOException("Error sending email with attachment", e);
        }
    }
}
