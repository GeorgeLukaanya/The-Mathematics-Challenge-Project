import Quiz.ParticipantLogin;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;

public class JavaServer {
    // Database URL
    static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
    static final String USER = "root";
    static final String PASS = "";

    public static void
    main(String[] args) {
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
                    ParticipantLogin participantLogin = new ParticipantLogin();
                    LoginHandler loginHandler = new LoginHandler(conn, out, scanner, representativeMenu);

                    boolean clientRunning = true;
                    while (clientRunning) {
                        if (scanner.hasNextLine()) {
                            String command = scanner.nextLine();
                            System.out.println("Command received: " + command); // Debugging output

                            try {
                                if (command.startsWith("Register ")) {
                                    // Process registration command using RegistrationHandler
                                    String result = registrationHandler.processRegistrationCommand(command, conn);
                                    out.println(result);
                                } else if (command.startsWith("Login ") && command.split(" ").length == 3) {
                                    String[] commandParts = command.split(" ");
                                    String username = commandParts[1];

                                    // Check which table the username belongs to
                                    if (usernameExistsInTable(conn, username, "schoolrepresentative")) {
                                        String result = loginHandler.processLoginCommand(command);
                                        out.println(result);
                                        if (result.equals("Representative login successful")) {
                                            representativeMenu.showMenu();
                                        }
                                    } else if (usernameExistsInTable(conn, username, "acceptedparticipants")) {
                                        String result = participantLogin.handleLogin(conn, commandParts[1], commandParts[2], out, scanner);
                                        if (result.equals("Participant login successful")) {
                                            continue; // Stay in the participant menu
                                        } else {
                                            out.println(result);
                                        }
                                    } else {
                                        out.println("Invalid username.");
                                    }
                                } else if (command.startsWith("viewApplicants") || command.startsWith("confirm")) {
                                    // Handle viewApplicants and confirm commands in representativeMenu
                                    representativeMenu.showMenu();
                                } else if (command.equalsIgnoreCase("exit")) {
                                    out.println("Server closing.");
                                    clientRunning = false; // Exit the client loop
                                } else {
                                    out.println("Invalid command.");
                                }
                            } catch (Exception e) {
                                out.println("An error occurred during login.");
                                e.printStackTrace();
                            }
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

    private static boolean usernameExistsInTable(Connection conn, String username, String tableName) throws SQLException {
        String query = "SELECT username FROM `" + tableName + "` WHERE username = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean exists = resultSet.next();
            resultSet.close();
            return exists;
        }
    }
}
