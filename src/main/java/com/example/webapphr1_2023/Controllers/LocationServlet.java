package com.example.webapphr1_2023.Controllers;

import com.example.webapphr1_2023.Beans.Employee;
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
        String action = req.getParameter("action") == null ? "listar" : req.getParameter("action");

        RequestDispatcher view;

        switch(action){
            case "listar":

                LocationDao locationdao = new LocationDao();
                ArrayList<Location> listaLocation=locationdao.listarLocation();
                req.setAttribute("listaLocation", listaLocation);
                view = req.getRequestDispatcher("location/list.jsp");
                view.forward(req, resp);
                break;
            case "crear":
                view = req.getRequestDispatcher("location/nuevaLocation.jsp");
                view.forward(req, resp);
                break;

            case "editar":
                if (req.getParameter("id") != null) {
                    int locationId = Integer.parseInt(req.getParameter("id"));
                    Location location = locationdao.obtenerLocation(locationId);

                    if (location != null) {
                        // Lógica para cargar datos necesarios si es necesario
                        // Por ejemplo, listas de países, regiones, etc.
                        req.setAttribute("location", location);
                        view = req.getRequestDispatcher("location/editarLocation.jsp");
                        view.forward(req, resp);
                    } else {
                        resp.sendRedirect("LocationServlet?action=listar");
                    }
                } else {
                    resp.sendRedirect("LocationServlet?action=listar");
                }
                break;

        }
        //req.setAttribute("locationList", new ArrayList<>());
        view = req.getRequestDispatcher("location/list.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}