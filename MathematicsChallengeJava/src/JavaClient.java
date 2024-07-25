import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JavaClient {
    // ANSI escape codes for coloring text in blue
    private static final String BLUE = "\u001B[34m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
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
            boolean loggedIn = false;
            boolean participantLogin = false;
            while (true) {
                if (!loggedIn && !participantLogin) {
                    System.out.println("Enter a command: \nRegister <username> <firstname> <lastname> <emailAddress> <date_of_birth YYYY-MM-DD> <school_registration_number> <image_file>\nLogin <username> <password>\nLogin <username> <schoolRegNo>");
                    String command = scanner.nextLine();
                    out.println(command);
                    out.flush();

                    // Read and print the server's response
                    String response = in.readLine();
                    System.out.println(response);

                    if (response != null && response.equals("Representative login successful")) {
                        loggedIn = true;
                    } else if (response != null && response.equals("Participant login successful")) {
                        participantLogin = true;
                    } else if (response != null && response.equals("Registration successful")) {
                        System.out.println("Please log in.");
                    } else if (response != null && response.equals("An error occurred during login.")) {
                        System.out.println("Login failed. Please try again.");
                    }
                } else if (loggedIn) {
                    // Handle representative menu commands
                    while (loggedIn) {
                        System.out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
                        String command = scanner.nextLine();
                        out.println(command);
                        out.flush();

                        // Read and print the server's response
                        String response;
                        while ((response = in.readLine()) != null) {
                            System.out.println(response);
                            if (response.equals("Logged out")) {
                                loggedIn = false;
                                break;
                            }
                        }
                    }
                } else if (participantLogin) {
                    // Handle participant menu commands
                    while (participantLogin) {
                        System.out.println("Enter command: viewchallenges");
                        String command = scanner.nextLine();
                        out.println(command);
                        out.flush();

                        // Read and print the server's response
                        String response;
                        while ((response = in.readLine()) != null) {
                            System.out.println(response);
                            if (response.equals("Displaying challenges...")) {
                                participantLogin = false; // Stay in the participant menu
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
