package org.example.soquete.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.print("Digite a região geográfica (Ex: America/Sao_Paulo): ");
            String region = userInput.readLine();

            out.println(region);  // Envia a região para o servidor

            String serverResponse = in.readLine();  // Recebe a resposta
            System.out.println("Resposta do servidor: " + serverResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
