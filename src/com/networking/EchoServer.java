package com.networking;

import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                (new Echoer(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            System.out.println("Server Exception: " + e.getMessage());
        }
    }
}
