package org.srestrepo.webapp.ejb.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.webapp.ejb.service.StatefulCDIServiceEJB;

import java.io.IOException;

@WebServlet("/stateful-cdi")
public class ServletForStatefulCDIEJB extends HttpServlet {

    @Inject // @EJB won't work because it does not take in count contexts
    private StatefulCDIServiceEJB stateful1; // It's going to look up the bean from the EJB pool and register it into the CDI container.

    @Inject // @EJB won't work because it does not take in count contexts
    private StatefulCDIServiceEJB stateful2; // It's going to gather the bean from the CDI container, not the EJB pool anymore.

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("serviceEJB equal to serviceEJB2: " +  stateful1.equals(stateful2));
       /*
        * Request 1: serviceEJB equal to serviceEJB2: true
        * Request 2: serviceEJB equal to serviceEJB2: true
        * If the beans are RequestScoped, for example, the objects are always to be the same within the same request.
        * */

        req.setAttribute("greeting", stateful1.greeting("Santiago"));
        req.setAttribute("greeting2", stateful2.greeting("John"));

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
