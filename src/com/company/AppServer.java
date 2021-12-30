package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AppServer {
    public final ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            AppServer server = new AppServer();
            server.start();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot start the server", e);
        }
    }

    public AppServer() throws IOException {
        serverSocket = new ServerSocket(8080);
    }

    public void start() throws IOException {
        System.out.println("Game server started");
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from: "+clientSocket.getInetAddress());
            GameSession gameSession = new GameSession(clientSocket);
            Thread t = new Thread(gameSession);
            t.start();
        }
    }
}
