package org.srestrepo.apiservlet.webapp.session.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Initializing Application");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servletContext.log("Destroying Application");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("Initializing Request");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        servletContext.log("Destroying Request");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Creating Session");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Destroying Session");
    }
}
