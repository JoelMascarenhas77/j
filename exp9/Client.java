import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try (
            Socket socket = new Socket("localhost", 5000);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in)
            )
        ) {
            // Read welcome message from server
            System.out.println(in.readLine());

            String command;
            while (true) {
                System.out.print("Enter Command (get/set/exit): ");
                command = userInput.readLine();
                out.println(command);

                if (command.equalsIgnoreCase("set")) {
                    System.out.print("Enter Value: ");
                    out.println(userInput.readLine());
                }

                String response;
                while ((response = in.readLine()) != null) {
                    System.out.println(response);
                    if (response.equals("Disconnecting...") || response.startsWith("Updated") || response.startsWith("Accessed") || response.equals("Invalid Command") || response.startsWith("Invalid")) {
                        break;
                    }
                }

                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
