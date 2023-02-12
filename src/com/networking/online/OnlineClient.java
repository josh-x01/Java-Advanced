package com.networking.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class OnlineClient {
    private static int port = 7988;
    private static String hostname = "192.168.102.216";
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket(hostname, port);
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String send = "";
            String receive = "";

            while (true) {
                System.out.print("Received message: ");
                receive = dis.readUTF();
                if (receive.equals("Stop")) {
                    break;
                }
                System.out.println(receive);
                System.out.print("Send message: ");
                send = scanner.nextLine();
                dos.writeUTF(send);
                if (send.equals("Stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}