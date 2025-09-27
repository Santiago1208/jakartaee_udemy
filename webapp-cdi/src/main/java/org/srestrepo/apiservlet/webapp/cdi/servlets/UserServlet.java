package org.srestrepo.apiservlet.webapp.cdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cdi.models.User;
import org.srestrepo.apiservlet.webapp.cdi.services.LoginService;
import org.srestrepo.apiservlet.webapp.cdi.services.LoginSessionService;
import org.srestrepo.apiservlet.webapp.cdi.services.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();

        LoginService loginService = new LoginSessionService();
        Optional<String> username = loginService.getUsername(req);

        req.setAttribute("username", username);
        req.setAttribute("users", users);
        req.setAttribute("title", req.getAttribute("title") + ": Users");
        getServletContext().getRequestDispatcher("/view-users.jsp").forward(req, resp);
    }
}
