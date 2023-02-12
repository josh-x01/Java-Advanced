package com.networking.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class OnlineServer {
    private static int port = 4321;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println(dis.readUTF());
                dos.writeUTF(new Scanner(System.in).nextLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
