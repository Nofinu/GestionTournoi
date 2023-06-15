package com.example.gestion_tournoi.service;

import com.example.gestion_tournoi.Model.Team;
import com.example.gestion_tournoi.Model.Tournament;
import com.example.gestion_tournoi.repository.TeamRepository;
import com.example.gestion_tournoi.repository.TournamentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TournamentService {
    private final SessionFactory _sessionFactory;
    private Session session;
    private TournamentRepository tournamentRepository;

    public TournamentService(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    public boolean createTournament(String name, String date) {
        boolean result = false;
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        df.setLenient(false);
        try {
            Date dateTournament = df.parse(date);
            Tournament tournament = new Tournament(name, dateTournament);
            session = _sessionFactory.openSession();
            session.beginTransaction();
            tournamentRepository = new TournamentRepository(session);
            tournamentRepository.create(tournament);
            session.getTransaction().commit();
            result = true;
        } catch (ParseException ignore) {

        } catch (Exception e) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ignored) {

            }
        } finally {
            if(session!= null && session.isOpen()){
                session.close();
            }
        }
        return result;
    }


    public boolean addTeamToTournament(Tournament tournament, Team team) {
        boolean result = false;
        if (tournament.addTeam(team)) {
            session = _sessionFactory.openSession();
            try {
                session.beginTransaction();
                tournamentRepository = new TournamentRepository(session);
                tournamentRepository.update(tournament);
                session.getTransaction().commit();
                result = true;
            } catch (Exception e){
                try{
                    session.getTransaction().rollback();
                }catch (Exception ignore){

                }
            }finally {
                session.close();
            }
        }
        return result;
    }

    public Tournament findTournamentById (int id){
        Tournament tournament =null;
        session = _sessionFactory.openSession();
        tournamentRepository = new TournamentRepository(session);
        try{
            tournament = tournamentRepository.findById(id);
        }catch (Exception ignore){

        }finally {
            session.close();
        }
        return tournament;
    }

    public List<Tournament> findAllTournament(){
        List<Tournament> tournaments =null;
        session = _sessionFactory.openSession();
        tournamentRepository = new TournamentRepository(session);
        try{
            tournaments = tournamentRepository.findAll();
        }catch (Exception ignore){

        }finally {
            session.close();
        }
        return tournaments;
    }
}
