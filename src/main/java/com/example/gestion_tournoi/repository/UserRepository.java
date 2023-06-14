package com.example.gestion_tournoi.repository;

import com.example.gestion_tournoi.Model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class UserRepository extends Repository<User> {
    public UserRepository(Session _session) {
        super(_session);
    }

    @Override
    public User findById(int id) {
        Query<User> userQuery = _session.createQuery("from User where id = :id", User.class);
        userQuery.setParameter("id",id);
        return userQuery.uniqueResult();
    }

    @Override
    public List<User> findAll() {
        return _session.createQuery("from User ", User.class).list();
    }

    public User findByUserName (String userName){
        Query<User> userQuery = _session.createQuery("from User where userName = :username");
        userQuery.setParameter("username",userName);
        return userQuery.uniqueResult();
    }
}
