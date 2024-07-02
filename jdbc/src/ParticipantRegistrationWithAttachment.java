import java.io.*;
import java.sql.*;
import java.util.*;

public class ParticipantRegistrationWithAttachment {

    // Database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/Mathematics_Challenge";
    // Database credentials
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            boolean registrationNumberValid = false;
            String registrationNumber = "";

            while (!registrationNumberValid) {
                // Capture participant details
                System.out.println("Enter the following details to register:");

                System.out.print("Username: ");
                String userName = scanner.nextLine();

                System.out.print("First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Last Name: ");
                String lastName = scanner.nextLine();

                System.out.print("Email Address: ");
                String emailAddress = scanner.nextLine();

                System.out.print("Date of Birth (YYYY-MM-DD): ");
                String dateOfBirth = scanner.nextLine();

                System.out.print("Registration Number: ");
                registrationNumber = scanner.nextLine();

                System.out.print("Image File Path (e.g., /path/to/image.png): ");
                String imageFilePath = scanner.nextLine();

                // Query to check if the registration number exists and get the representative email
                String query = "SELECT RepresetativeEmail FROM School WHERE SchoolRegNo = ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, registrationNumber);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    registrationNumberValid = true;
                    // Get the representative email from the database
                    String representativeEmail = rs.getString("RepresetativeEmail");

                    if (isValidEmail(representativeEmail)) {
                        // Display the representative email
                        System.out.println("Representative Email: " + representativeEmail);

                        // Save the details to a file
                        try {
                            String filePath = saveDetailsToFile(userName, firstName, lastName, emailAddress, dateOfBirth, registrationNumber, imageFilePath);
                            System.out.println("Participant details saved to: " + filePath);
                        } catch (IOException e) {
                            System.out.println("An error occurred while saving the details to the file: " + e.getMessage());
                            e.printStackTrace();
                        }

                        System.out.println("Registration completed successfully.");
                    } else {
                        System.out.println("Invalid email address retrieved from the database.");
                    }
                } else {
                    System.out.println("Invalid registration number. Please re-enter the details.");
                }
            }

        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }

    // Method to validate email address format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    // Method to save participant details to a file
    public static String saveDetailsToFile(String userName, String firstName, String lastName, String emailAddress, String dob, String regNum, String imageFile) throws IOException {
        // Create a file to save the details
        File file = new File("participant_details.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Participant Details:\n");
            writer.write("Username: " + userName + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Last Name: " + lastName + "\n");
            writer.write("Email Address: " + emailAddress + "\n");
            writer.write("Date of Birth: " + dob + "\n");
            writer.write("Registration Number: " + regNum + "\n");
            writer.write("Image File Path: " + imageFile + "\n"); // Save the local file path of the image
        }
        return file.getAbsolutePath();
    }
}
