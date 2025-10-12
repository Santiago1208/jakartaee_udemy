package org.srestrepo.webapp.ejb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.service.StatefulServiceEJB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

@WebServlet("/jndi")
public class ServletForJNDILookup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatefulServiceEJB stateful1;
        StatefulServiceEJB stateful2;

        try {
            InitialContext context = new InitialContext();
            stateful1 = (StatefulServiceEJB) context.lookup("java:global/webapp-ejb/StatefulServiceEJB!org.srestrepo.webapp.ejb.service.StatefulServiceEJB");
            stateful2 = (StatefulServiceEJB) context.lookup("java:global/webapp-ejb/StatefulServiceEJB!org.srestrepo.webapp.ejb.service.StatefulServiceEJB");

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("serviceEJB equal to serviceEJB2: " +  stateful1.equals(stateful2));

        req.setAttribute("greeting", stateful1.greeting("Santiago"));
        req.setAttribute("greeting2", stateful2.greeting("John"));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
