import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;

public class JavaServer {
    // Database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        // SMTP configuration for Mailtrap
        String smtpHost = "sandbox.smtp.mailtrap.io";
        String smtpPort = "2525";
        String smtpUsername = "f3683be4bf7d0e";
        String smtpPassword = "4bb6532709da20";

        // Path to participant details file in project folder
        String filePath = System.getProperty("user.dir") + File.separator + "participant_details.txt";

        // Create an instance of RegistrationHandler
        RegistrationHandler registrationHandler = new RegistrationHandler(smtpHost, smtpPort, smtpUsername, smtpPassword);

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     Scanner scanner = new Scanner(socket.getInputStream());
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

                    System.out.println("Client connected.");

                    RepresentativeMenu representativeMenu = new RepresentativeMenu(conn, out, scanner, filePath);

                    while (true) {
                        String command = scanner.nextLine();
                        System.out.println("Command received: " + command); // Debugging output

                        if (command.startsWith("Register ")) {
                            // Process registration command using RegistrationHandler
                            String result = registrationHandler.processRegistrationCommand(command, conn);
                            out.println(result);
                        } else if (command.startsWith("Login ")) {
                            // Create an instance of LoginHandler and process login command
                            LoginHandler loginHandler = new LoginHandler(conn, out, scanner);
                            String result = loginHandler.processLoginCommand(command);
                            out.println(result);
                            if (result.equals("Login successful")) {
                                representativeMenu.showMenu();
                            }
                        } else {
                            out.println("Invalid command.");
                        }

                        // Re-prompt for command if necessary
                        if (command == null || !command.startsWith("Login ")) {
                            out.println("Enter a command: \nRegister <username> <firstname> <lastname> <emailAddress> <date_of_birth YYYY-MM-DD> <school_registration_number> <image_file>\nLogin <username> <password>");
                        }
                    }
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
