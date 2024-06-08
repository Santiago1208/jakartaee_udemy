package org.srestrepo.webapp.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/query")
public class QueryParamServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(QueryParamServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String hello = request.getParameter("hello");
        String name = request.getParameter("name");

        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html>");
            printWriter.println("    <head>");
            printWriter.println("        <meta charset=\"UTF-8\">");
            printWriter.println("        <title>Query Parameters</title>");
            printWriter.println("    </head>");
            printWriter.println("    <body>");
            printWriter.println("        <h1>Query Parameters</h1>");
            if (hello != null && name != null) {
                printWriter.println("        <h2>The greeting received was: " + hello + " " + name + "</h2>");
            } else if (hello != null) {
                printWriter.println("        <h2>The greeting received was: " + hello + "</h2>");
            } else if (name != null) {
                printWriter.println("        <h2>Hello, my name is: " + name + "</h2>");
            } else {
                printWriter.println("        <h2>No parameters were passed in</h2>");
            }
            try {
                int code = Integer.parseInt(request.getParameter("code"));
                printWriter.println("        <h3>The code you sent is " + code + "</h3>");
            } catch (NumberFormatException e) {
                printWriter.println("        <h3>No code was sent</h3>");
            }
            printWriter.println("    </body>");
            printWriter.println("</html>");
        } catch (IOException e) {
            log.throwing("QueryParamServlet", "doGet", e);
        }
    }
}
