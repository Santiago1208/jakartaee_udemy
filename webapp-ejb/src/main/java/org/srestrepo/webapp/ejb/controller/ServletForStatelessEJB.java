package org.srestrepo.webapp.ejb.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.service.StatelessServiceEJB;

import java.io.IOException;

@WebServlet("/stateless")
public class ServletForStatelessEJB extends HttpServlet {

    @EJB // Will always pull the bean from the EJB pool
    private StatelessServiceEJB stateless1;

    @EJB // Will always pull the bean from the EJB pool
    private StatelessServiceEJB stateless2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("serviceEJB equal to stateless: " +  stateless1.equals(stateless2));
       /*
        * Request 1: serviceEJB equal to stateless: true
        * Request 2: serviceEJB equal to stateless: true
        * This is because stateless beans instances are shared with all clients, unless one specific instance
        * is buzzy by one thread it will create a different instance.
        */

        req.setAttribute("greeting", stateless1.greeting("Santiago"));
        req.setAttribute("greeting2", stateless2.greeting("John"));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
