/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Palalae
 */
public class GameClient {

    public GameClient(String serverAddress, int PORT) throws IOException {
        String request = null;
        //do {
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out
                = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))) {
            // Send a request to the server
            Scanner keyboard;
            while (!"exit".equals(request) && !"stop".equals(request)) {
                keyboard = null;
                keyboard = new Scanner(System.in);
                request = null;
                request = keyboard.nextLine();
                out.println(request);

                String response = in.readLine();
                System.out.println(response);

            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
        //} while (!"exit".equals(request));

    }

}
