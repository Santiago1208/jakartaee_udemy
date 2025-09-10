package org.srestrepo.apiservlet.webapp.authjdbc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.authjdbc.services.UserService;
import org.srestrepo.apiservlet.webapp.authjdbc.services.UserServiceImpl;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/users/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("jdbcConnection");
        UserService userService = new UserServiceImpl(connection);
        long userId;
        try {
            userId = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            userId = 0L;
        }
        if (userId > 0L) {
            userService.delete(userId);
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "The User ID is required");
        }
    }
}
