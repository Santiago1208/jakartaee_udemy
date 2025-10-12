package org.srestrepo.webapp.ejb.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.service.ServiceEJB;

import java.io.IOException;

@WebServlet("/index")
public class ExampleServlet extends HttpServlet {

    @EJB
    private ServiceEJB serviceEJB;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greeting", serviceEJB.greeting("Santiago"));
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
