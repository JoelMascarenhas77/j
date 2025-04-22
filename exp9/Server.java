import java.io.*;
import java.net.*;

public class Server {

    private static int sharedVariable = 50; // Initial value of shared variable

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("SharedMemoryServer started on port 5000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                );
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                out.println("Connected to SharedMemoryServer. Type 'exit' to quit.");

                String command;
                while ((command = in.readLine()) != null) {
                    if (command.equalsIgnoreCase("get")) {
                        out.println("Accessed Shared Variable: " + sharedVariable);
                    } else if (command.equalsIgnoreCase("set")) {
                        out.println("Enter Value:");
                        try {
                            int newValue = Integer.parseInt(in.readLine());
                            sharedVariable = newValue;
                            out.println("Updated Shared Variable: " + sharedVariable);
                        } catch (NumberFormatException e) {
                            out.println("Invalid Value. Please enter an integer.");
                        }
                    } else if (command.equalsIgnoreCase("exit")) {
                        out.println("Disconnecting...");
                        break;
                    } else {
                        out.println("Invalid Command");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
