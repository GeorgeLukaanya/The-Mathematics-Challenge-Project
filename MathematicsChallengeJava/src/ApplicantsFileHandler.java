import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;

public class ApplicantsFileHandler {
    private String host;
    private String port;
    private String username;
    private String password;

    public ApplicantsFileHandler(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public List<String[]> fetchApplicantsFromEmails() {
        List<String[]> applicants = new ArrayList<>();

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", host);
        properties.put("mail.imaps.port", port);
        properties.put("mail.imaps.ssl.enable", "true");
        properties.put("mail.imaps.auth", "true");

        try {
            Session emailSession = Session.getInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            // Search for unread emails
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message message : messages) {
                if (message.getSubject().contains("Application Status Notification")) {
                    processMessage(message, applicants);
                }
                // Mark the message as read
                message.setFlag(Flags.Flag.SEEN, true);
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return applicants;
    }

    private void processMessage(Message message, List<String[]> applicants) {
        try {
            if (message.isMimeType("multipart/*")) {
                Multipart multipart = (Multipart) message.getContent();

                for (int i = 0; i < multipart.getCount(); i++) {
                    BodyPart part = multipart.getBodyPart(i);

                    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                        MimeBodyPart mimeBodyPart = (MimeBodyPart) part;
                        String fileName = mimeBodyPart.getFileName();
                        File file = new File(fileName);

                        // Save the attachment
                        mimeBodyPart.saveFile(file);

                        // Process the attachment
                        List<String[]> details = readApplicantDetailsFromFile(file);
                        applicants.addAll(details);

                        // Delete the file after processing
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String[]> readApplicantDetailsFromFile(File file) {
        List<String[]> applicantDetails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 7) {
                    applicantDetails.add(details);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return applicantDetails;
    }

    public void removeApplicant(String[] applicantDetails) {
        // Implementation to remove an applicant from the file
        // This method remains unchanged from the previous implementation
    }
}
