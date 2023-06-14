package com.example.gestion_tournoi.control;

import com.example.gestion_tournoi.Model.Team;
import com.example.gestion_tournoi.service.TeamService;
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
import java.util.List;

@WebServlet(name = "teams",value = "/teams")
public class TeamServlet extends HttpServlet {
    private SessionFactory sessionFactory;
    private TeamService teamService;
    public void init() {
        sessionFactory = HibernateSession.getSessionFactory();
        teamService = new TeamService(sessionFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("isLogged")!=null && (boolean) session.getAttribute("isLogged")){
            List<Team> teams = teamService.findAllTeam();
            request.setAttribute("teams",teams);
            request.getRequestDispatcher(Definition.PATH_VIEWS+"TeamsPage.jsp").forward(request,response);
        }else{
            response.sendRedirect(Definition.BASE_URL);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = "";
        if(request.getParameter("teamName") != null){
            String teamName = request.getParameter("teamName");
            if(teamService.createTeam(teamName)){
                response.sendRedirect("");
            }else{
                errorMessage = "error when adding the team";

            }
        }else{
            errorMessage="empty input";
        }
        if(!errorMessage.equals("")){
            request.setAttribute("errorMessage",errorMessage);
            request.getRequestDispatcher(Definition.PATH_VIEWS+"TeamsPage.jsp").forward(request,response);
        }
    }

    public void destroy() {

    }
}
