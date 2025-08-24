package org.srestrepo.apiservlet.webapp.cookie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cookie.services.LoginService;
import org.srestrepo.apiservlet.webapp.cookie.services.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    static final String USERNAME = "admin";
    static final String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService loginService = new LoginServiceImpl();
        Optional<String> usernameOptional = loginService.getUsername(request);

        if (usernameOptional.isEmpty()) {
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Hello " + usernameOptional.get() + "!</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Welcome back! You are logged in already</h1>");
                out.println("       <p><a href='" + request.getContextPath() + "/index.html'>Go Back</a></p>");
                out.println("       <p><a href='" + request.getContextPath() + "/logout'>Logout</a></p>");
                out.println("   </body>");
                out.println("</html>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            Cookie usernameCookie = new Cookie("username", username);
            response.addCookie(usernameCookie);

            response.sendRedirect(request.getContextPath() + "/login.html");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login incorrect");
        }
    }
}
