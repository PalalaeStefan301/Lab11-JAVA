/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Lab11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.PlayerService;

/**
 *
 * @author Palalae
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/players")
public class Controller {

    private PlayerService playerservice;

    @RequestMapping("/hello")
    public String sayHello() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/getPlayers")
    public Collection<Player> getPlayers() {
        try {
            Connection connection = Database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from players;");
            Player aux = new Player();
            while (result.next()) {

                aux.setName(result.getString("name"));
                aux.setColour(result.getString("colour"));
                playerservice.getAllPlayers().add(aux);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return playerservice.getAllPlayers();
    }

    @PostMapping("/addPlayer")
    public void addPlayer(@RequestParam String name, @RequestParam String colour) {
        Connection connection;
        Statement statement = null;
        try {
            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS players (id int primary key unique auto_increment, name varchar(20) not null, colour varchar(20))");
            statement.execute("CREATE TABLE IF NOT EXISTS games "
                    + "(id int primary key unique auto_increment,"
                    + " name varchar(100) not null,"
                    + " id_player_1 int not null references players on delete restrict,"
                    + " id_player_2 int not null references players on delete restrict )");
            PreparedStatement posted = connection.prepareStatement("insert into players(name,colour) values('" + name + "'," + colour + ");");
            posted.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
    
    /*@DeleteMapping("/delete")
    public String deletePlayer(@PathVariable (value="name") String name)
    {
        playerservice.deletePlayer(name);
    }*/
}
