package Net;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 9999;
    private static ExecutorService dichVu = Executors.newFixedThreadPool(10); // Sử dụng 10 luồng cho server

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server dang chay tren cong " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Da ket noi toi client: " + clientSocket);
                dichVu.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
