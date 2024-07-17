import java.io.*;
import java.net.*;
import java.sql.*;

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

        // Create an instance of RegistrationHandler
        RegistrationHandler registrationHandler = new RegistrationHandler(smtpHost, smtpPort, smtpUsername, smtpPassword);

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

                    System.out.println("Client connected.");

                    // Read the command from the client
                    String command = in.readLine();
                    System.out.println("Command received: " + command); // Debugging output

                    // Validate and process the command
                    if (command == null) {
                        out.println("Invalid command.");
                    } else if (command.startsWith("Register ")) {
                        // Process registration command using RegistrationHandler
                        String result = registrationHandler.processRegistrationCommand(command, conn);
                        out.println(result);
                    } else if (command.startsWith("Login ")) {
                        // Create an instance of LoginHandler and process login command
                        LoginHandler loginHandler = new LoginHandler(conn, out, in, registrationHandler.getFilePath());
                        String result = loginHandler.processLoginCommand(command);
                        out.println(result);
                        out.println();
                        out.flush(); // Ensure the output is sent to the client
                        if (result.equals("Login successful")) {
                            RepresentativeMenu representativeMenu = new RepresentativeMenu(conn, out, in, registrationHandler.getFilePath());
                            representativeMenu.showMenu();
                        }
                    } else {
                        out.println("Invalid command.");
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
