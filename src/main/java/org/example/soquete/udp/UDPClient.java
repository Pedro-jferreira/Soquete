package org.example.soquete.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 8080;
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            System.out.print("Digite a região geográfica (Ex: America/Sao_Paulo): ");
            String region = scanner.nextLine();

            byte[] sendData = region.getBytes(StandardCharsets.UTF_8);
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);

            // Envia o pacote para o servidor
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            // Prepara o buffer para receber a resposta
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            clientSocket.receive(receivePacket);  // Espera pela resposta do servidor

            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
            System.out.println("Resposta do servidor: " + serverResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}