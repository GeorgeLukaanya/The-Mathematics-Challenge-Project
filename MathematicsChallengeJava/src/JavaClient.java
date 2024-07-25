import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class JavaClient {
    // ANSI escape codes for coloring text in blue
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        int retryCount = 0;
        final int maxRetries = 3;
        while (retryCount < maxRetries) {
            try (Socket socket = new Socket("localhost", 12345);
                 Scanner scanner = new Scanner(System.in);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                System.out.println("Connected to the server.");

                // Display welcome message
                System.out.println(BLUE + "=================================================================" + RESET);
                System.out.println(BLUE + "            Welcome to The International Education Services       " + RESET);
                System.out.println(BLUE + "                   Mathematics Challenge Platform                  " + RESET);
                System.out.println(BLUE + "=================================================================" + RESET);
                System.out.println(BLUE + " Whether you are a Participant or a Representative, we welcome you " + RESET);
                System.out.println(BLUE + "   to the ultimate platform for mathematical challenges and more!  " + RESET);
                System.out.println(BLUE + "=================================================================\n" + RESET);

                // Handle login and menu commands
                handleCommands(scanner, out, in);

                // Exit the loop if connection is successful
                break;

            } catch (IOException e) {
                retryCount++;
                System.err.println("Connection error: " + e.getMessage());
                if (retryCount < maxRetries) {
                    System.out.println("Retrying... (" + retryCount + "/" + maxRetries + ")");
                } else {
                    System.out.println("Failed to connect after " + maxRetries + " attempts. Exiting.");
                    System.exit(1);
                }
            }
        }
    }

    private static void handleCommands(Scanner scanner, PrintWriter out, BufferedReader in) {
        boolean loggedIn = false;
        boolean participantLogin = false;

        while (true) {
            if (!loggedIn && !participantLogin) {
                System.out.println("Enter a command: \nRegister <username> <firstname> <lastname> <emailAddress> <date_of_birth YYYY-MM-DD> <school_registration_number> <image_file>\nLogin <username> <password>\nLogin <username> <schoolRegNo>");
                if (!scanner.hasNextLine()) break;
                String command = scanner.nextLine();
                out.println(command);
                out.flush();

                // Read and print the server's response
                String response;
                try {
                    response = in.readLine();
                    if (response != null) {
                        System.out.println(response);
                        if (response.equals("Representative login successful")) {
                            loggedIn = true;
                        } else if (response.equals("Participant login successful")) {
                            participantLogin = true;
                        } else if (response.equals("Registration successful")) {
                            System.out.println("Please log in.");
                        } else if (response.equals("An error occurred during login.")) {
                            System.out.println("Login failed. Please try again.");
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading server response: " + e.getMessage());
                }
            } else if (loggedIn) {
                // Handle representative menu commands
                while (loggedIn) {
                    System.out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
                    if (!scanner.hasNextLine()) break;
                    String command = scanner.nextLine();
                    out.println(command);
                    out.flush();

                    // Read and print the server's response
                    try {
                        String response;
                        while ((response = in.readLine()) != null) {
                            System.out.println(response);
                            if (response.equals("Logged out")) {
                                loggedIn = false;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading server response: " + e.getMessage());
                    }
                }
            } else if (participantLogin) {
                // Handle participant menu commands
                while (participantLogin) {
                    System.out.println("Enter command: viewchallenges");
                    if (!scanner.hasNextLine()) break;
                    String command = scanner.nextLine();
                    out.println(command);
                    out.flush();

                    // Read and print the server's response
                    try {
                        String response;
                        while ((response = in.readLine()) != null) {
                            System.out.println(response);
                            if (response.equals("Displaying challenges...")) {
                                participantLogin = false; // Stay in the participant menu
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading server response: " + e.getMessage());
                    }
                }
            }
        }
    }
}
