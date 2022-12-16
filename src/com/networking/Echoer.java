package com.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Echoer extends Thread{
    private Socket socket;
    Echoer(Socket socket) {
        this.socket = socket;
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Server Exception: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                //
            }
        }
    }
}
