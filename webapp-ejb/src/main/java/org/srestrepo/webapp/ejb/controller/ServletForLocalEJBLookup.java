package org.srestrepo.webapp.ejb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.service.LocalServiceEJB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/local-ejb")
public class ServletForLocalEJBLookup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalServiceEJB local1;
        LocalServiceEJB local2;

        try {
            InitialContext context = new InitialContext();
            local1 = (LocalServiceEJB) context.lookup("java:global/webapp-ejb/LocalServiceEJBImpl!org.srestrepo.webapp.ejb.service.LocalServiceEJB");
            local2 = (LocalServiceEJB) context.lookup("java:global/webapp-ejb/LocalServiceEJBImpl!org.srestrepo.webapp.ejb.service.LocalServiceEJB");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("serviceEJB equal to serviceEJB2: " +  local1.equals(local2));

        req.setAttribute("greeting", local1.greeting("Santiago"));
        req.setAttribute("greeting2", local2.greeting("John"));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
