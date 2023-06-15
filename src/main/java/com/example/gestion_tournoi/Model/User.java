package com.example.gestion_tournoi.Model;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected String status;
    @Column(unique = true)
    protected String userName;
    private String game;
    private String password;
    @ManyToMany(mappedBy = "players",fetch = FetchType.EAGER)
    private List<Team> teams;


    public User(String status, String userName,String password) {
        this.status = status;
        this.userName = userName;
        this.password = BCrypt.hashpw(password,BCrypt.gensalt(10));
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
