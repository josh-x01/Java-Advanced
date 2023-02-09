package com.networking.socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 7672);
            System.out.println("[OK] Client Connected!");

            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            dos.writeUTF("Hello server!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
