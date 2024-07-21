import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JavaClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             Scanner scanner = new Scanner(System.in);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the server.");

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
