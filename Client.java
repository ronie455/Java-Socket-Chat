
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 5000;

        try (
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
            );
            BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in)
            )
        ) {
            System.out.println(in.readLine());
            System.out.println("Type a message (or exit):");

            String message;
            while ((message = keyboard.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                out.println(message);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
