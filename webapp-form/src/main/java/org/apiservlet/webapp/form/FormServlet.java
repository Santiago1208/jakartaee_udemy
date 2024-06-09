package org.apiservlet.webapp.form;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Logger;

@WebServlet("/register")
public class FormServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(FormServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String[] programming = request.getParameterValues("programming");
        String[] roles = request.getParameterValues("roles");

        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html>");
            printWriter.println("    <head>");
            printWriter.println("        <meta charset=\"UTF-8\">");
            printWriter.println("        <title>Form Result</title>");
            printWriter.println("    </head>");
            printWriter.println("    <body>");
            printWriter.println("        <h1>Form Result</h1>");
            printWriter.println("        <ul>");
            printWriter.println("           <li>Username: " + username + "</li>");
            printWriter.println("           <li>Password: " + password + "</li>");
            printWriter.println("           <li>E-Mail: " + email + "</li>");
            printWriter.println("           <li>Country: " + country + "</li>");
            printWriter.println("           <li>Programming Languages: <ul>");
            Arrays.asList(programming).forEach(p -> printWriter.println("<li>" + p + "</li>"));
            printWriter.println("           </ul></li>");
            printWriter.println("           <li>Roles: <ul>");
            Arrays.asList(roles).forEach(r -> printWriter.println("<li>" + r + "</li>"));
            printWriter.println("           </ul></li>");
            printWriter.println("        </ul>");
            printWriter.println("    </body>");
            printWriter.println("</html>");
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "doPost", e);
        }
    }
}
