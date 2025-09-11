package org.srestrepo.apiservlet.webapp.cdi.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cdi.models.User;
import org.srestrepo.apiservlet.webapp.cdi.services.UserService;
import org.srestrepo.apiservlet.webapp.cdi.services.UserServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/users/add")
public class UserFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId;
        try {
            userId = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            userId = 0L;
        }
        User user = new User();
        String title = req.getAttribute("title") + ": New User";
        if (userId > 0L) {
            Connection connection = (Connection) req.getAttribute("jdbcConnection");
            UserService userService = new UserServiceImpl(connection);
            Optional<User> userOptional = userService.findById(userId);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                title = req.getAttribute("title") + ": Edit User";
            }
        }
        req.setAttribute("user", user);
        req.setAttribute("title", title);
        getServletContext().getRequestDispatcher("/user-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("jdbcConnection");
        UserService userService = new UserServiceImpl(connection);
        Map<String, String> errors = new HashMap<>();

        String username = req.getParameter("username");
        if (username == null || username.isBlank()) {
            errors.put("username", "Please enter a username");
        }

        String password = req.getParameter("password");
        if (password == null || password.isBlank()) {
            errors.put("password", "Please enter a password");
        }

        String email = req.getParameter("email");
        if (email == null || email.isBlank()) {
            errors.put("email", "Please enter an email");
        }

        long userId;
        try {
            userId = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            userId = 0L;
        }

        if (userId == 0L) {
            boolean existsByUsername = userService.existsByUsername(username);
            if (existsByUsername) {
                errors.put("username", "This username is already taken");
            }

            boolean existsByEmail = userService.existsByEmail(email);
            if (existsByEmail) {
                errors.put("email", "This email is already registered");
            }
        }

        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        if (errors.isEmpty()) {
            userService.save(user);
            resp.sendRedirect(req.getContextPath() + "/users");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("user", user);
            String title = userId > 0L ? req.getAttribute("title") + ": Edit User" : req.getAttribute("title") + ": New User";
            req.setAttribute("title", title);
            getServletContext().getRequestDispatcher("/user-form.jsp").forward(req, resp);
        }
    }
}
