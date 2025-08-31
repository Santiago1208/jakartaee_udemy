package org.srestrepo.apiservlet.webapp.jdbc.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.srestrepo.apiservlet.webapp.jdbc.models.Cart;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Initializing Application");
        // This will be application scoped
        servletContext.setAttribute("globalMessage", "Some global message for the Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destroying Application");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Initializing Request");
        // This will be request scoped
        sre.getServletRequest().setAttribute("requestMessage", "Some message for the Request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destroying Request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Creating Session");
        Cart cart = new Cart();
        HttpSession session = se.getSession();
        session.setAttribute("cart", cart);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destroying Session");
    }
}
