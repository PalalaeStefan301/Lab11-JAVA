/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLogic;

/**
 *
 * @author Palalae
 */
public class Game {

    private String name;
    private Board board;
    private Player player1, player2;

    public Game(String name) {
        this.name = name;
        board = new Board();
        player1 = new Player("white");
        player2 = new Player("black");
    }

    public Game() {

    }

    public void addPlayer() {
        if(player1==null)
            player1= new Player("white");
        else
            player2= new Player("black");
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
