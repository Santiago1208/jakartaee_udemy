package org.srestrepo.apiservlet.webapp.headers.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.headers.models.Product;
import org.srestrepo.apiservlet.webapp.headers.services.ProductService;
import org.srestrepo.apiservlet.webapp.headers.services.ProductServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet({"/products.xls", "/products.html", "/products"})
public class ProductXLSServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductXLSServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.getProducts();

        response.setContentType("text/html;charset=UTF-8");
        String servletPath = request.getServletPath();
        boolean isXLS = servletPath.endsWith(".xls");

        if (isXLS) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=products.xls");
        }
        try (PrintWriter printWriter = response.getWriter()) {
            if (!isXLS) {
                printWriter.println("<!DOCTYPE html>");
                printWriter.println("<html>");
                printWriter.println("    <head>");
                printWriter.println("        <meta charset=\"UTF-8\">");
                printWriter.println("        <title>Product List</title>");
                printWriter.println("    </head>");
                printWriter.println("    <body>");
                printWriter.println("        <h1>Product List</h1>");
                printWriter.println("        <p><a href=\"" + request.getContextPath() + "products.xls\">Export to XLS</a></p>");
            }
            printWriter.println("        <table>");
            printWriter.println("           <tr>");
            printWriter.println("               <th>ID</th>");
            printWriter.println("               <th>Name</th>");
            printWriter.println("               <th>Type</th>");
            printWriter.println("               <th>Price</th>");
            printWriter.println("           </tr>");
            products.forEach(p -> {
                printWriter.println("            <tr>");
                printWriter.println("               <td>" + p.getId() + "</td>");
                printWriter.println("               <td>" + p.getName() + "</td>");
                printWriter.println("               <td>" + p.getType() + "</td>");
                printWriter.println("               <td>" + p.getPrice() + "</td>");
                printWriter.println("            </tr>");
            });
            printWriter.println("        </table>");

            if (!isXLS) {
                printWriter.println("    </body>");
                printWriter.println("</html>");
            }
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "", e);
        }

    }
}
