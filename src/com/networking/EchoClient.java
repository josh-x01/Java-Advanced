package com.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)){
            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String respose;


            do {
                System.out.print("Enter string to be echoed: ");
                echoString = scanner.nextLine();
                stringToEcho.println(echoString);

                if (!echoString.equals("exit")) {
                    respose = echoString;
                    System.out.println(echoString);
                }
            } while (!echoString.equals("exit"));

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
