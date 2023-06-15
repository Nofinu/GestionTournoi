package com.example.gestion_tournoi.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_team;

    private String name;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "player_Team", joinColumns = @JoinColumn(name = "id_team"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<User> players;

    public Team(String name) {
        this.name = name;
    }

    public Team() {
    }

    public int getId_team() {
        return id_team;
    }

    public void setId_team(int id_team) {
        this.id_team = id_team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public boolean addUser (User user){
        if(user != null){
            this.players.add(user);
            return true;
        }
        return false;
    }



}
