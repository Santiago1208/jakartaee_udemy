package org.srestrepo.apiservlet.webapp.bootstrap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.srestrepo.apiservlet.webapp.bootstrap.services.LoginService;
import org.srestrepo.apiservlet.webapp.bootstrap.services.LoginSessionService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    static final String USERNAME = "admin";
    static final String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService loginService = new LoginSessionService();
        Optional<String> usernameOptional = loginService.getUsername(request);

        if (usernameOptional.isEmpty()) {
            request.setAttribute("title", request.getAttribute("title") + ": Login");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.setAttribute("title", request.getAttribute("title") + ": Welcome!");
            getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username", username);

            response.sendRedirect(request.getContextPath() + "/login.html");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login incorrect");
        }
    }
}
