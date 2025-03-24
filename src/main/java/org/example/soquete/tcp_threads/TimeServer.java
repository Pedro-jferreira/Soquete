package org.example.soquete.tcp_threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Servidor iniciado. Aguardando conexões...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Cada cliente será atendido por uma thread
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String region = in.readLine();
            System.out.println("Região recebida: " + region);

            String response;
            try {
                ZonedDateTime time = ZonedDateTime.now(ZoneId.of(region));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
                response = "Hora local em " + region + ": " + time.format(formatter);
            } catch (DateTimeException e) {
                response = "Erro: Região '" + region + "' inválida!";
            }

            out.println(response);
            System.out.println("Resposta enviada para " + clientSocket.getInetAddress());

        } catch (IOException e) {
            System.out.println("Erro ao atender cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Conexão encerrada com " + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
