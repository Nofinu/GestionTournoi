package com.example.gestion_tournoi.service;

import com.example.gestion_tournoi.Model.User;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {

    private HttpSession _session;

    public LoginService(HttpSession session) {
        this._session = session;
    }

    public boolean isLogged (){
        return _session.getAttribute("isLogged") != null && (boolean) _session.getAttribute("isLogged");
    }

    public boolean isAdmin (){
        return _session.getAttribute("isAdmin") != null && (boolean) _session.getAttribute("isAdmin");
    }

    public static boolean IsConnected (User user, String password){
        return BCrypt.checkpw(password, user.getPassword());
    }
}
