import Quiz.ParticipantLogin;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;

public class JavaServer {
    static final String DB_URL = "jdbc:mysql://localhost:3306/math-challengez";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        String smtpHost = "sandbox.smtp.mailtrap.io";
        String smtpPort = "2525";
        String smtpUsername = "f3683be4bf7d0e";
        String smtpPassword = "4bb6532709da20";

        String filePath = System.getProperty("user.dir") + File.separator + "participant_details.txt";

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

                    handleClientCommands(scanner, out, conn, registrationHandler, representativeMenu, participantLogin, loginHandler);

                } catch (IOException | SQLException e) {
                    System.err.println("Connection error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void handleClientCommands(Scanner scanner, PrintWriter out, Connection conn,
                                             RegistrationHandler registrationHandler, RepresentativeMenu representativeMenu,
                                             ParticipantLogin participantLogin, LoginHandler loginHandler) {
        while (true) {
            try {
                String command = scanner.nextLine();
                System.out.println("Command received: " + command);

                if (command.startsWith("Register ")) {
                    String result = registrationHandler.processRegistrationCommand(command, conn);
                    out.println(result);
                } else if (command.startsWith("Login ") && command.split(" ").length == 3) {
                    String[] commandParts = command.split(" ");
                    String username = commandParts[1];

                    if (usernameExistsInTable(conn, username, "schoolrepresentative")) {
                        String result = loginHandler.processLoginCommand(command);
                        out.println(result);
                        if (result.equals("Representative login successful")) {
                            representativeMenu.showMenu();
                        }
                    } else if (usernameExistsInTable(conn, username, "acceptedparticipants")) {
                        String result = participantLogin.handleLogin(conn, commandParts[1], commandParts[2], out, scanner);
                        out.println(result);
                        if (result.equals("Participant login successful")) {
                            continue; // Stay in the participant menu
                        }
                    } else {
                        out.println("Invalid username.");
                    }
                } else if (command.startsWith("viewApplicants") || command.startsWith("confirm")) {
                    representativeMenu.showMenu();
                } else {
                    out.println("Invalid command.");
                }
            } catch (Exception e) {
                out.println("An error occurred during command processing.");
                e.printStackTrace();
            }
            out.println("Enter a command: \nRegister <username> <firstname> <lastname> <emailAddress> <date_of_birth YYYY-MM-DD> <school_registration_number> <image_file>\nLogin <username> <password>\nLogin <username> <schoolRegNo>");
        }
    }

    private static boolean usernameExistsInTable(Connection conn, String username, String tableName) throws SQLException {
        String query = "SELECT username FROM `" + tableName + "` WHERE username = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }
}
