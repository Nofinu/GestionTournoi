package com.example.gestion_tournoi.control;

import com.example.gestion_tournoi.Model.User;
import com.example.gestion_tournoi.service.LoginService;
import com.example.gestion_tournoi.service.UserService;
import com.example.gestion_tournoi.util.Definition;
import com.example.gestion_tournoi.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.SessionFactory;


import java.io.IOException;

@WebServlet(name = "connection",value = "/connection")
public class ConnectionServlet extends HttpServlet {
    private SessionFactory sessionFactory;
    private UserService userService;

    public void init (){
        sessionFactory = HibernateSession.getSessionFactory();
        userService = new UserService(sessionFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("type")!=null && request.getParameter("type").equals("login")){
            request.getRequestDispatcher(Definition.PATH_VIEWS+"Connection.jsp").forward(request,response);
        } else if (request.getParameter("type")!=null && request.getParameter("type").equals("loOut")) {
            session.setAttribute("isLogged",false);
            session.setAttribute("isAdmin",false);
            session.setAttribute("user",null);
            response.sendRedirect(Definition.BASE_URL);
        } else{
            request.getRequestDispatcher(Definition.PATH_VIEWS+"Register.jsp").forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage="";
        HttpSession session = request.getSession();

        if(request.getParameter("type")!= null && request.getParameter("type").equals("login")){
            if(request.getParameter("userName")!=null && request.getParameter("password")!= null){
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                User user = userService.findByUsername(userName);
                if(user !=null){
                    if(LoginService.IsConnected(user,password)){
                        session.setAttribute("user",user);
                        session.setAttribute("isLogged",true);
                        if(user.getStatus().equals("Admin")){
                            session.setAttribute("isAdmin",true);
                        }else{
                            session.setAttribute("isAdmin",false);
                        }
                        response.sendRedirect(Definition.BASE_URL);
                    }else{
                        errorMessage = "wrong password";
                    }
                }else{
                    errorMessage="incorrect Username";
                }
            }
            else{
                errorMessage = "input not fill";
            }
        }
        else if(request.getParameter("type")!= null && request.getParameter("type").equals("register")){
            if(request.getParameter("userName")!=null && request.getParameter("password")!= null && request.getParameter("game")!=null){
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                String game = request.getParameter("game");
                String status = request.getParameter("player");

               if(userService.createUser(status,userName,game,password)){
                   session.setAttribute("isLogged",true);
                   User user = userService.findByUsername(userName);
                   session.setAttribute("user",user);
                   if(status.equals("Admin")){
                       request.setAttribute("isAdmin",true);
                   }else{
                       request.setAttribute("isAdmin",false);
                   }
                   response.sendRedirect("");
               }
            }else{
                errorMessage = "input not fill";
            }
        }
        if(!errorMessage.equals("")){
            request.setAttribute("errorMessage",errorMessage);
            request.getRequestDispatcher(Definition.PATH_VIEWS+"Connection.jsp").forward(request,response);
        }

    }

    public void destroy(){
        sessionFactory.close();
    }
}
