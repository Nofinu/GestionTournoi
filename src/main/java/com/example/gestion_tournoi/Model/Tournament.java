package com.example.gestion_tournoi.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tournament;
    private String name;
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tournament_team",joinColumns = @JoinColumn(name = "id_tournament"),
            inverseJoinColumns = @JoinColumn(name = "id_team"))
    private List<Team> teams;

    public Tournament(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Tournament() {
    }

    public int getId_tournament() {
        return id_tournament;
    }

    public void setId_tournament(int id_tournament) {
        this.id_tournament = id_tournament;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public boolean addTeam (Team team){
        if(team != null){
            this.teams.add(team);
            return true;
        }
        return false;
    }
}
