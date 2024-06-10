package org.srestrepo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ForwardServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            getServletContext().getRequestDispatcher("/products.html").forward(request, response);
        } catch (IOException | ServletException e) {
            log.throwing(this.getClass().getName(), "doGet", e);
        }
    }
}
