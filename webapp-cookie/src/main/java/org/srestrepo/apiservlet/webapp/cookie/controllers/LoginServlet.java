package org.srestrepo.apiservlet.webapp.cookie.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static final String USERNAME = "admin";
    static final String PASSWORD = "12345";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.println("<!DOCTYPE html>");
                printWriter.println("<html>");
                printWriter.println("    <head>");
                printWriter.println("        <meta charset=\"UTF-8\">");
                printWriter.println("        <title>Correct Login</title>");
                printWriter.println("    </head>");
                printWriter.println("    <body>");
                printWriter.println("        <h1>Correct Login</h1>");
                printWriter.println("        <h3>Hello! " + username + " you logged in successfully!</h3>");
                printWriter.println("    </body>");
                printWriter.println("</html>");
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Login incorrect");
        }
    }
}
