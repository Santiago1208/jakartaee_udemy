package org.srestrepo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebServlet("/request-headers")
public class RequestHttpHeadersServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RequestHttpHeadersServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();
        String clientIp = request.getRemoteAddr();
        String ip = request.getLocalAddr(); // Server's IP
        int port = request.getLocalPort(); // Server's port
        String scheme = request.getScheme();
        String host = request.getHeader("host");
        String theUrl = scheme + "://" + host + contextPath + servletPath;

        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html>");
            printWriter.println("    <head>");
            printWriter.println("        <meta charset=\"UTF-8\">");
            printWriter.println("        <title>Request HTTP Headers</title>");
            printWriter.println("    </head>");
            printWriter.println("    <body>");
            printWriter.println("        <h1>Request HTTP Headers</h1>");
            printWriter.println("        <ul>");
            printWriter.println("           <li>Method: " + method + "</li>");
            printWriter.println("           <li>URI:" + uri + "</li>");
            printWriter.println("           <li>URL: " + url + "</li>");
            printWriter.println("           <li>Context Path: " + contextPath + "</li>");
            printWriter.println("           <li>Servlet Path: " + servletPath + "</li>");
            printWriter.println("           <li>Server IP: " + ip + "</li>");
            printWriter.println("           <li>Client IP: " + clientIp + "</li>");
            printWriter.println("           <li>Server Port: " + port + "</li>");
            printWriter.println("           <li>Scheme: " + scheme + "</li>");
            printWriter.println("           <li>Host: " + host + "</li>");
            printWriter.println("           <li>URL from Scratch: " + theUrl + "</li>");

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                printWriter.println("           <li>" + headerName + ": " + headerValue + "</li>");
            }

            printWriter.println("        </ul>");
            printWriter.println("    </body>");
            printWriter.println("</html>");
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "", e);
        }
    }
}
