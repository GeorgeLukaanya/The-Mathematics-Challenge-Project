import java.io.*;
import java.net.*;
import java.util.*;

public class JavaClient {

    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        Scanner scanner = new Scanner(System.in);

        try {
            // Connect to the server
            socket = new Socket("localhost", 12345); // Connect to the server at port 12345
            System.out.println("Connected to the server.");

            // Set up input and output streams
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Capture participant details from the user
            System.out.println("Enter the following details to register:");

            System.out.print("Username: ");
            String userName = scanner.nextLine();
            out.println(userName);

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();
            out.println(firstName);

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();
            out.println(lastName);

            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();
            out.println(emailAddress);

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();
            out.println(dateOfBirth);

            System.out.print("Registration Number: ");
            String registrationNumber = scanner.nextLine();
            out.println(registrationNumber);

            System.out.print("Image File Path (e.g., /path/to/image.png): ");
            String imageFilePath = scanner.nextLine();
            out.println(imageFilePath);

            // Receive and print the representative email from the server
            String response = in.readLine();
            System.out.println("Server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
