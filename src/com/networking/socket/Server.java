package com.networking.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(7672);
            System.out.println("[OK] Server started");
            Socket socket = server.accept();
            System.out.println("Waiting client request . . .");

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            System.out.println(dis.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
