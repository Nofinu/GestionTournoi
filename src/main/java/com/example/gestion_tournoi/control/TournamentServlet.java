package com.example.gestion_tournoi.control;

import com.example.gestion_tournoi.Model.Tournament;
import com.example.gestion_tournoi.service.TeamService;
import com.example.gestion_tournoi.service.TournamentService;
import com.example.gestion_tournoi.util.Definition;
import com.example.gestion_tournoi.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "tournamentServlet",value = "/tournament")
public class TournamentServlet extends HttpServlet {
    private SessionFactory sessionFactory;
    private TournamentService tournamentService;
    public void init() {
        sessionFactory = HibernateSession.getSessionFactory();
        tournamentService = new TournamentService(sessionFactory);
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("type")!=null && request.getParameter("type").equals("add")){
            request.getRequestDispatcher(Definition.PATH_VIEWS+"TournamentForm.jsp").forward(request,response);
        }else{
            List<Tournament> tournaments = tournamentService.findAllTournament();
            request.setAttribute("tournaments",tournaments);
            for (Tournament t:tournaments) {
                System.out.println(t.getName());
            }
            request.getRequestDispatcher(Definition.PATH_VIEWS+"Tournament.jsp").forward(request,response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "";
        if(request.getParameter("type")!= null && request.getParameter("type").equals("add")){
            if(request.getParameter("EquipeName")!=null && request.getParameter("date")!=null && request.getParameter("hours")!=null &&request.getParameter("mins")!=null){
                String equipeName = request.getParameter("EquipeName");
                String dateStr = request.getParameter("date") +"-"+ request.getParameter("hours") +request.getParameter("mins") + "00";

                if(tournamentService.createTournament(equipeName,dateStr)){
                    response.sendRedirect("tournament");
                }else{
                    errorMessage = "error when create the Tournament";
                }
            }
        }
        if(errorMessage != ""){
            request.setAttribute("errorMessage",errorMessage);
            request.getRequestDispatcher(Definition.PATH_VIEWS+"TournamentForm.jsp").forward(request, response);
        }
    }
    public void destroy(){

    }



}
