package org.example.soquete.tcp;

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
                    System.out.println("Resposta enviada: " + response);
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}