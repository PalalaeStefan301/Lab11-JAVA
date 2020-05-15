/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Player;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Palalae
 */
@Repository
public class PlayerDao {

    private static Map<Integer, Player> players;

    static {
        players = new HashMap<Integer, Player>() {
            {
                put(1, new Player(1,"george", "white"));
                put(2, new Player(2,"george1", "black"));
                put(3, new Player(3,"george2", "white"));
            }

        };
    }

    public Collection<Player> getAllPlayers() {
        return this.players.values();
    }
}
