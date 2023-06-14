package com.example.gestion_tournoi.control;

import java.io.*;

import com.example.gestion_tournoi.service.LoginService;
import com.example.gestion_tournoi.util.Definition;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Home", value = "/")
public class HomeServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session =  request.getSession();
        LoginService loginService = new LoginService(session);
        boolean isLogged = loginService.isLogged();
        boolean isAdmin = loginService.isAdmin();
        request.setAttribute("isLogged",isLogged);
        request.setAttribute("isAdmin",isAdmin);
        request.getRequestDispatcher(Definition.PATH_VIEWS+"Home.jsp").forward(request,response);
    }

    public void destroy() {
    }
}