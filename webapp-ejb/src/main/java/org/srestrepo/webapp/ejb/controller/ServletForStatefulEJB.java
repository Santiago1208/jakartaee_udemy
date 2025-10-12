package org.srestrepo.webapp.ejb.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.service.StatefulServiceEJB;

import java.io.IOException;

@WebServlet("/stateful")
public class ServletForStatefulEJB extends HttpServlet {

    @EJB // Will always pull the bean from the EJB pool
    private StatefulServiceEJB stateful1;

    @EJB // Will always pull the bean from the EJB pool
    private StatefulServiceEJB stateful2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("serviceEJB equal to serviceEJB2: " +  stateful1.equals(stateful2));
       /*
        * Request 1: serviceEJB equal to serviceEJB2: false
        * Request 2: serviceEJB equal to serviceEJB2: false
        * Stateful beans are one-to-one to the clients requesting them. In this example, every attribute of the bean
        * will generate a new instance, however, if too many clients get in parallel to this servlet, no more instances
        * are going to be elaborated, since the context of the Servlet (ApplicationScoped) is going to be spread to the beans (Dependent).
        * */

        req.setAttribute("greeting", stateful1.greeting("Santiago"));
        req.setAttribute("greeting2", stateful2.greeting("John"));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
