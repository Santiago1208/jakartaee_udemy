package org.srestrepo.apiservlet.webapp.jpacdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jpacdi.services.UserService;

import java.io.IOException;

@WebServlet("/users/delete")
public class DeleteUserServlet extends HttpServlet {

    @Inject
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
