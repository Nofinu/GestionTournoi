package com.example.gestion_tournoi.repository;

import com.example.gestion_tournoi.Model.Tournament;
import com.example.gestion_tournoi.Model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TournamentRepository extends Repository<Tournament>{
    public TournamentRepository(Session _session) {
        super(_session);
    }

    @Override
    public Tournament findById(int id) {
        Query<Tournament> tournamentQuery = _session.createQuery("from Tournament where id_tournament = :id", Tournament.class);
        tournamentQuery.setParameter("id",id);
        return tournamentQuery.uniqueResult();
    }

    @Override
    public List<Tournament> findAll() {
        return _session.createQuery("from Tournament where id_tournament = :id", Tournament.class).list();
    }
}
