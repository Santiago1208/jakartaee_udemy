package org.apiservlet.webapp.form;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
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
            printWriter.println("           <li>" + username + "</li>");
            printWriter.println("           <li>" + password + "</li>");
            printWriter.println("           <li>" + email + "</li>");
            printWriter.println("        </ul>");
            printWriter.println("    </body>");
            printWriter.println("</html>");
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "doPost", e);
        }
    }
}
