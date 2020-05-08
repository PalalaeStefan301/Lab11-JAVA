/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import GameLogic.Game;
import GameLogic.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Palalae
 */
class ClientThread extends Thread {

    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String request = null;
            // Send the response to the oputput stream: server → client

            String raspuns = "Server received the request ... ";

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Game game = null;
            Player player1 = null, player2 = null;
            while (true) {
                request = in.readLine();
                if (request != null) {
                    System.out.println(request);
                }
                if (request == null || "exit".equals(request)) {
                    raspuns = "Client disconnected";

                } else {
                    switch (request) {
                        case "stop":
                            raspuns = "Server stopped";
                            out.println(raspuns);
                            out.flush();
                            socket.close();
                            System.exit(0);
                        case "create game":
                            game = new Game();
                            raspuns = "Game created";
                        case "join game":
                            if (game == null) {
                                raspuns = "Game doesnt exist";
                            } else {
                                game.addPlayer();
                                raspuns = "You joined an existing game";
                            }
                        case "submit move": // aici ar fi trebuit sa iau doar "submit move " din request ca sa se imdeplineasca conditia, inputul ar fi: "submit move B12"
                            if (game == null && player1 == null && player2 == null) {
                                raspuns = "First create a game and join in";
                            } else {

                                request = request.replace("submit move ", ""); // extrag doar miscarea
                                game.getBoard().generateMove(request, player1);
                            }

                    }
                }
                out.println(raspuns);
                out.flush();
            }

        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
