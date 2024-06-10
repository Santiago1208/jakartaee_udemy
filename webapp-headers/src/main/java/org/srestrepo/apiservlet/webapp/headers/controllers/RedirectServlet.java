package org.srestrepo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RedirectServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        /* This is one way to do it
        response.setHeader("Location", request.getContextPath() + "/products.html");
        response.setStatus(HttpServletResponse.SC_FOUND);
        */

        // This is another way, they are exactly the same, cause the same effect.
        try {
            response.sendRedirect(request.getContextPath() + "/products.html");
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "doGet", e);
        }
    }
}
