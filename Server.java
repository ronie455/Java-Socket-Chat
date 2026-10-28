
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected!");

                new Thread(() -> {
                    try (
                        BufferedReader in = new BufferedReader(
                            new InputStreamReader(client.getInputStream())
                        );
                        PrintWriter out = new PrintWriter(
                            client.getOutputStream(), true
                        )
                    ) {
                        out.println("Connected to Java Chat Server!");

                        String message;
                        while ((message = in.readLine()) != null) {
                            System.out.println("Client: " + message);
                            out.println("Server received: " + message);
                        }
                    } catch (IOException e) {
                        System.out.println("Client disconnected.");
                    } finally {
                        try {
                            client.close();
                        } catch (IOException ignored) {}
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
