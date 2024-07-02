import java.io.*;
import java.net.*;
import java.sql.*;

public class JavaServer {

    // Database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/Mathematics_Challenge";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        Connection conn = null;

        try {
            // Set up the server socket
            serverSocket = new ServerSocket(12345); // Use port 12345
            System.out.println("Server started. Waiting for clients...");

            // Wait for a client to connect
            socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Set up input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Open a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Read the details from the client
            String userName = in.readLine();
            String firstName = in.readLine();
            String lastName = in.readLine();
            String emailAddress = in.readLine();
            String dateOfBirth = in.readLine();
            String registrationNumber = in.readLine();
            String imageFilePath = in.readLine();

            System.out.println("Received details from client:");
            System.out.println("Username: " + userName);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email Address: " + emailAddress);
            System.out.println("Date of Birth: " + dateOfBirth);
            System.out.println("Registration Number: " + registrationNumber);
            System.out.println("Image File Path: " + imageFilePath);

            // Check registration number in the database
            String query = "SELECT RepresentativeEmail FROM School WHERE SchoolRegNo = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, registrationNumber);
            ResultSet rs = pstmt.executeQuery();

            String representativeEmail = null;
            if (rs.next()) {
                representativeEmail = rs.getString("RepresentativeEmail");
                out.println("Representative Email: " + representativeEmail);
            } else {
                out.println("No representative email found for the given registration number.");
            }

            // Save the details to a file
            saveDetailsToFile(userName, firstName, lastName, emailAddress, dateOfBirth, registrationNumber, imageFilePath, representativeEmail);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to save participant details to a file
    public static void saveDetailsToFile(String userName, String firstName, String lastName, String emailAddress, String dob, String regNum, String imageFile, String representativeEmail) {
        // Create a file to save the details
        File file = new File("participant_details.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
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
        } catch (IOException e) {
            System.out.println("An error occurred while saving the details to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
