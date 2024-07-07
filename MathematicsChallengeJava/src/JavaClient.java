import java.io.*;
import java.net.*;

public class JavaClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the server.");

            // Prompt the user to enter the registration command
            System.out.println("Enter the registration command (Register <username> <firstname> <lastname> <emailAddress> <date_of_birth> <school_registration_number> <image_file>):");
            String command = userIn.readLine();

            // Send the command to the server
            out.println(command);

            // Read and print the server's response
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
