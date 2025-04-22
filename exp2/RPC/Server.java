import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is waiting for client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String message = input.readUTF();
        System.out.println("Received from client: " + message);

        // Split the message
        String[] parts = message.split(" ");
        int operation = Integer.parseInt(parts[0]);
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int result = 0;

        switch (operation) {
            case 1:
                result = add(a, b); 
                break;
            case 2:
                result = sub(a, b) ;                                                                                                         
            default:
                break;
        }

        output.writeUTF("Result: " + result);
        socket.close();
        serverSocket.close();
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }
}
