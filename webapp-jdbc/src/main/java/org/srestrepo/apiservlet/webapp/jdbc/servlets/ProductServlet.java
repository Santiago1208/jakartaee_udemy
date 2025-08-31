package org.srestrepo.apiservlet.webapp.jdbc.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jdbc.models.Product;
import org.srestrepo.apiservlet.webapp.jdbc.services.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet({"/products.html", "/products"})
public class ProductServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.getProducts();

        LoginService loginService = new LoginSessionService();
        Optional<String> usernameOptional = loginService.getUsername(request);

        String requestMessage = (String) request.getAttribute("requestMessage");
        String appMessage = (String) getServletContext().getAttribute("globalMessage");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.println("<!DOCTYPE html>");
            printWriter.println("<html>");
            printWriter.println("    <head>");
            printWriter.println("        <meta charset=\"UTF-8\">");
            printWriter.println("        <title>Product List</title>");
            printWriter.println("    </head>");
            printWriter.println("    <body>");
            printWriter.println("        <h1>Product List</h1>");
            if (usernameOptional.isPresent()) {
                printWriter.println("        <div style='color: blue;'>Hello " + usernameOptional.get() + "!</div>");
            }
            printWriter.println("        <table>");
            printWriter.println("           <tr>");
            printWriter.println("               <th>ID</th>");
            printWriter.println("               <th>Name</th>");
            printWriter.println("               <th>Type</th>");
            if (usernameOptional.isPresent()) {
                printWriter.println("               <th>Price</th>");
                printWriter.println("               <th>Add</th>");
            }
            printWriter.println("           </tr>");
            products.forEach(p -> {
                printWriter.println("            <tr>");
                printWriter.println("               <td>" + p.getId() + "</td>");
                printWriter.println("               <td>" + p.getName() + "</td>");
                printWriter.println("               <td>" + p.getType() + "</td>");
                if (usernameOptional.isPresent()) {
                    printWriter.println("               <td>" + p.getPrice() + "</td>");
                    printWriter.println("               <td><a href=\"" + request.getContextPath() + "/cart/add?id=" + p.getId() + "\">Add to Cart</a></td>");
                }
                printWriter.println("            </tr>");
            });
            printWriter.println("        </table>");
            printWriter.println("        <p>" + appMessage + "</p>");
            printWriter.println("        <p>" + requestMessage + "</p>");
            printWriter.println("    </body>");
            printWriter.println("</html>");
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "", e);
        }

    }
}
