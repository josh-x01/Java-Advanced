package com.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{
    private Socket socket;
    Echoer(Socket socket) {
        this.socket = socket;
        System.out.println("Client connected!");
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String echoString = input.readLine();
                if (echoString.equals("exit"))
                    break;
                System.out.println("Echo from server: " + echoString);
            }
        } catch (IOException e) {
            System.out.println("Server Echoer Exception: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                //
            }
        }
    }
}
