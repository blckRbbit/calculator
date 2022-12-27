import service.ExpressionService;

import java.io.*;
import java.net.ServerSocket;

import static util.Util.getPort;

public class Main {
    private static final int PORT = getPort();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        ExpressionService service = new ExpressionService();

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            Connect connect = new Connect(server);
            while (true) {
                new Thread(() -> {
                    String response;
                    try {
                        String request = connect.read();
                        if (request==null) {
                            System.out.println("Waiting...");
                            return;
                        }
                        System.err.printf("Task:%n %s%n", request);
                        if (request.equalsIgnoreCase("q")) {
                            response = "Thanks for using the app! Goodbye...";
                            connect.write(response);
                            return;
                        }
                        response = service.run(request);
                        connect.write(response);
                        connect.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
