package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Location;
import com.example.webapphr1_2023.Daos.LocationDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LocationServlet", urlPatterns = {"/LocationServlet"})
public class LocationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null? "no" : req.getParameter("action");

        switch(action){
            case "listar":

                LocationDao locationdao = new LocationDao();
                ArrayList<Location> listaLocation=locationdao.listarLocation();
                


        }
        RequestDispatcher view;
        req.setAttribute("locationList", new ArrayList<>());
        view = req.getRequestDispatcher("location/list.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}