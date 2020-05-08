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
public class Board {
    private int[][] moves = new int[14][14];

    public int[][] getMoves() {
        return moves;
    }

    public void setMoves(int[][] moves) {
        this.moves = moves;
    }
    public void generateMove(String move,Player player)
    {
        char letter = move.charAt(0);
        int column = (int) letter;
        column-=65;
        move= move.replace(letter+"","");
        int row = Integer.parseInt(move);
        if("black".equals(player.getColour()))
        {
            moves[row][column]=-1;
        }
        else
        {
           moves[row][column]=1;
        }
    }
    
}
