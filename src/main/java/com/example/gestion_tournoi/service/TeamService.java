package com.example.gestion_tournoi.service;

import com.example.gestion_tournoi.Model.Team;
import com.example.gestion_tournoi.Model.User;
import com.example.gestion_tournoi.repository.TeamRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TeamService {
    private final SessionFactory _sessionFactory;
    private Session session;
    private TeamRepository teamRepository;

    public TeamService(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    public boolean createTeam(String name) {
        boolean result = false;
        Team team = new Team(name);
        session = _sessionFactory.openSession();

        try {
            session.beginTransaction();
            teamRepository = new TeamRepository(session);
            teamRepository.create(team);
            session.getTransaction().commit();
            result = true;
        } catch (Exception e) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ignored) {

            }
        } finally {
            session.close();
        }
        return result;
    }

    public boolean addUserToTeam (Team team, User user){
        boolean result = false;
        if(team.addUser(user)){
            session =_sessionFactory.openSession();
            try{
                session.beginTransaction();
                teamRepository = new TeamRepository(session);
                teamRepository.update(team);
                session.getTransaction().commit();
                result = true;
            }catch (Exception e){
                try{
                    session.getTransaction().rollback();
                }catch (Exception ignored){

                }
            }finally {
                session.close();
            }
        }
        return result;
    }

    public Team findByIdTeam(int id) {
        Team team = null;
        session = _sessionFactory.openSession();
        teamRepository = new TeamRepository(session);
        try {
            team = teamRepository.findById(id);
        } catch (Exception ignored) {

        } finally {
            session.close();
        }
        return team;
    }

    public List<Team> findAllTeam() {
        List<Team> teams = null;
        try{
            session= _sessionFactory.openSession();
            teamRepository = new TeamRepository(session);
            teams = teamRepository.findAll();
        }catch (Exception ignored){

        }finally {
            session.close();
        }
        return teams;
    }
}
