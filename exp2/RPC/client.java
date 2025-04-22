import java.io.*;
import java.net.*;
import java.util.Scanner;
public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);

        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());

        // Send a command to the server
        String command;
        int op; int val1;int val2; 
        Scanner scan = new Scanner(System.in);
        System.out.println("enter operation \n add 1 \n sub 2\n");
        op= scan.nextInt();
        System.out.println("enter operand1");
        val1 = scan.nextInt();
        System.out.println("enter operand2");
        val2 = scan.nextInt();
        output.writeUTF(op +" "+val1+" "+val2);

        // Receive the result
        String reply = input.readUTF();
        System.out.println("Received from server: " + reply);

        socket.close();
    }
}
