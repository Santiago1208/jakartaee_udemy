package org.srestrepo.apiservlet.webapp.session.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.session.services.LoginService;
import org.srestrepo.apiservlet.webapp.session.services.LoginServiceImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginService loginService = new LoginServiceImpl();
        Optional<String> usernameOptional = loginService.getUsername(request);
        if (usernameOptional.isPresent()) {
            Cookie usernameCookie = new Cookie("username", "");
            usernameCookie.setMaxAge(0);
            response.addCookie(usernameCookie);
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
