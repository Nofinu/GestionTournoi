package com.example.gestion_tournoi.repository;

import com.example.gestion_tournoi.Model.Team;
import com.example.gestion_tournoi.Model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TeamRepository extends Repository<Team> {
    public TeamRepository(Session _session) {
        super(_session);
    }

    @Override
    public Team findById(int id) {
        Query<Team> teamQuery = _session.createQuery("from Team where id_team = :id", Team.class);
        teamQuery.setParameter("id",id);
        return teamQuery.uniqueResult();
    }

    @Override
    public List<Team> findAll() {
        return  _session.createQuery("from Team", Team.class).list();
    }
}
