package org.example.soquete.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 8080;
        byte[] buffer = new byte[1024];

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP aguardando na porta " + PORT + "...");

            while (true) {
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(requestPacket);  // Espera por uma mensagem do cliente

                String region = new String(requestPacket.getData(), 0, requestPacket.getLength(), StandardCharsets.UTF_8);
                System.out.println("Região recebida: " + region);

                String response;
                try {
                    ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of(region));
                    response = "Hora local em " + region + ": " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
                } catch (Exception e) {
                    response = "Região inválida: " + region;
                }

                byte[] responseData = response.getBytes(StandardCharsets.UTF_8);
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                serverSocket.send(responsePacket);  // Envia resposta para o cliente
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
