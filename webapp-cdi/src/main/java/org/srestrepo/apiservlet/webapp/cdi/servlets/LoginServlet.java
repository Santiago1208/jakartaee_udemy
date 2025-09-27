package org.srestrepo.apiservlet.webapp.cdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.srestrepo.apiservlet.webapp.cdi.models.User;
import org.srestrepo.apiservlet.webapp.cdi.services.LoginService;
import org.srestrepo.apiservlet.webapp.cdi.services.LoginSessionService;
import org.srestrepo.apiservlet.webapp.cdi.services.UserService;

import java.io.IOException;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private UserService userService;

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

        Optional<User> userOptional = userService.login(username, password);
        if (userOptional.isPresent()) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username", username);

            response.sendRedirect(request.getContextPath() + "/login.html");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login incorrect");
        }
    }
}
