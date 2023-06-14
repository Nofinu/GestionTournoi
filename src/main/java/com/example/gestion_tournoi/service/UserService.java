package com.example.gestion_tournoi.service;

import com.example.gestion_tournoi.Model.User;
import com.example.gestion_tournoi.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserService {
    private final SessionFactory _sessionFactory;
    private Session session;
    private UserRepository userRepository;

    public UserService(SessionFactory sessionFactory) {
        this._sessionFactory = sessionFactory;
    }

    public boolean createUser (String status,String userName,String game,String password){
        boolean result = false;
        User user = new User(status,userName,password);
        if(status.equals("Player")){
            user.setGame(game);
        }
        session = _sessionFactory.openSession();
        try{
            session.beginTransaction();
            userRepository = new UserRepository(session);
            userRepository.create(user);
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
        return result;
    }

    public User findUserById (int id){
        User user = null;
        session = _sessionFactory.openSession();
        userRepository = new UserRepository(session);
        try{
            user = userRepository.findById(id);
        }catch (Exception ignored){

        }finally {
        session.close();
        }
        return user;
    }

    public List<User> findAllUser (){
        List<User> users = null;
        session = _sessionFactory.openSession();
        userRepository = new UserRepository(session);
        try{
            users = userRepository.findAll();
        }catch (Exception ignored){

        }finally {
            session.close();
        }
        return users;
    }

    public User findByUsername (String username){
        User user = null;
        session = _sessionFactory.openSession();
        userRepository = new UserRepository(session);
        try{
            user = userRepository.findByUserName(username);
        }catch (Exception ignored){

        }finally {
            session.close();
        }
        return user;
    }
}
