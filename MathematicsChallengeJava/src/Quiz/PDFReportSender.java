package Quiz;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PDFReportSender {

    /**
     * Generates a PDF report with questions and correct answers.
     *
     * @param questions a list of Question objects containing the questions and correct answers
     * @param pdfFilePath the path where the PDF file will be saved
     */
    public static void generatePDFReport(List<Question> questions, String pdfFilePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
            document.open();

            document.add(new Paragraph("Mathematics Challenge Questions Report"));
            document.add(new Paragraph("==================================="));
            document.add(new Paragraph("\n"));

            for (Question question : questions) {
                document.add(new Paragraph("Question: " + question.getQuestionText()));
                document.add(new Paragraph("Correct Answer: " + question.getCorrectAnswer()));
                document.add(new Paragraph("\n"));
            }

            document.close();
            System.out.println("PDF report generated successfully!");

        } catch (DocumentException | IOException e) {
            System.err.println("Error generating PDF report: " + e.getMessage());
        }
    }

    /**
     * Sends a PDF report as an email attachment.
     *
     * @param recipientEmail the email address of the recipient
     * @param subject the subject of the email
     * @param body the body text of the email
     * @param pdfFilePath the path to the PDF file
     */
    public static void sendPDFReport(String recipientEmail, String subject, String body, String pdfFilePath) {
        // Mailtrap credentials
        final String username = "f3683be4bf7d0e"; // Mailtrap username
        final String password = "4bb6532709da20"; // Mailtrap password

        // Set up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "2525");

        // Create a session with Mailtrap credentials
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Check if the PDF file exists
            File pdfFile = new File(pdfFilePath);
            if (!pdfFile.exists()) {
                System.err.println("PDF file not found: " + pdfFilePath);
                return;
            }

            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@example.com")); // Replace with the sender's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Attach the PDF file
            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pdfFilePath);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(new File(pdfFilePath).getName());
            multipart.addBodyPart(attachmentPart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("PDF report sent successfully!");

        } catch (MessagingException e) {
            System.err.println("Error generating or sending PDF report: " + e.getMessage());
        }
    }
}