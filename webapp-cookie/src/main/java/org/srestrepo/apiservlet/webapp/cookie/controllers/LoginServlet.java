package org.srestrepo.apiservlet.webapp.cookie.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    static final String USERNAME = "admin";
    static final String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]);
        Optional<String> usernameOptional = Arrays.stream(cookies)
                .filter(cookie -> "username".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst();

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
