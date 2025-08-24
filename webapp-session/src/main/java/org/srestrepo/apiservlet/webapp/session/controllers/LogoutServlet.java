package org.srestrepo.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.srestrepo.apiservlet.webapp.session.services.LoginService;
import org.srestrepo.apiservlet.webapp.session.services.LoginSessionService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService loginService = new LoginSessionService();
        Optional<String> usernameOptional = loginService.getUsername(request);
        if (usernameOptional.isPresent()) {
            HttpSession httpSession = request.getSession();
            httpSession.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
