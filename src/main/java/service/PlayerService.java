/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Dao.PlayerDao;
import java.util.Collection;
import model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Palalae
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerDao playerDao;

    public Collection<Player> getAllPlayers(){
        return playerDao.getAllPlayers();
    }
    

}
