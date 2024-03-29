package com.example.gestion_tournoi.repository;

import org.hibernate.Session;

import java.util.List;

public abstract class Repository<T> {
    protected Session _session;

    public Repository(Session _session) {
        this._session = _session;
    }
    public boolean create(T o) {
        _session.save(o);
        return true;
    }

    public boolean update(T o) {
        _session.update(o);
        return true;
    }

    public boolean delete(T o) {
        _session.delete(o);
        return true;
    }

    public abstract T findById(int id);

    public abstract List<T> findAll();
}
