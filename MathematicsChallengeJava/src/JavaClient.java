import java.io.*;
import java.net.*;

public class JavaClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Connected to the server.");

            // Initial menu
            while (true) {
                System.out.println("Enter a command: \n1. Register <username> <firstname> <lastname> <emailAddress> <date_of_birth YYYY-MM-DD> <school_registration_number> <image_file>\n2. Login <username> <password>");
                String command = userIn.readLine();

                // Send the command to the server
                out.println(command);

                // Read and print the server's response
                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                    if (response.equals("Login successful")) {
                        handleRepresentativeMenu(in, out, userIn);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleRepresentativeMenu(BufferedReader in, PrintWriter out, BufferedReader userIn) throws IOException {
        while (true) {
            System.out.println("Enter a command: \nviewApplicants\nconfirm yes/no <username>\nLogout");
            String command = userIn.readLine();

            // Send the command to the server
            out.println(command);

            // Read and print the server's response
            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);
                if (response.equals("Logged out")) {
                    return;
                }
            }
        }
    }
}
