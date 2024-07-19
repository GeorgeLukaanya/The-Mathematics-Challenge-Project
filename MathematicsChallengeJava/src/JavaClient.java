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

            // Prompt the user to enter a command
            while (true) {
                System.out.println("Enter a command: \nRegister <username> <firstname> <lastname> <emailAddress> <date_of_birth YYYY-MM-DD> <school_registration_number> <image_file>\nLogin <username> <password>");
                String command = scanner.nextLine();

                // Send the command to the server
                out.println(command);
                out.flush(); // Ensure the command is sent to the server

                // Read and print the server's response
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                    if (response.equals("Login successful")) {
                        break;
                    }
                }

                // Re-prompt for command if necessary
                if (response != null && response.equals("Login successful")) {
                    break;
                }
            }

            // Handle representative menu commands
            while (true) {
                System.out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
                String command = scanner.nextLine();
                // Send the command to the server
                out.println(command);
                out.flush(); // Ensure the command is sent to the server

                // Read and print the server's response
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                    if (response.equals("Logged out")) {
                        break;
                    }
                }

                // Exit the loop if logged out
                if (response != null && response.equals("Logged out")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
