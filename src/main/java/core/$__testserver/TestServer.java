package core.$__testserver;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestServer {

    private static final int SERVER_PORT = 10001;

    public static void main(String[] args) {
        BlockingQueue<Socket> loginQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Socket> roomRequestQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Socket> gameRequestQueue = new LinkedBlockingQueue<>();

        // Start request handlers
        new Thread(new LoginRequestHandler(loginQueue)).start();
        new Thread(new RoomRequestHandler(roomRequestQueue)).start();
        new Thread(new GameRequestHandler(gameRequestQueue)).start();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started on port: " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Determine request type
                RequestType requestType = determineRequestType(clientSocket);

                // Route the request to the appropriate handler
                switch (requestType) {
                    case LOGIN -> loginQueue.add(clientSocket);
                    case ROOMLIST -> roomRequestQueue.add(clientSocket);
                    case CONNECTCHAT -> gameRequestQueue.add(clientSocket);
                    default -> {
                        System.out.println("Unknown request type. Closing connection.");
                        clientSocket.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static RequestType determineRequestType(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String jsonRequest = in.readLine();
            Gson gson = new Gson();
            DTO requestDTO = gson.fromJson(jsonRequest, DTO.class);
            return requestDTO.getRequestType();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if request type cannot be determined
        }
    }
}
