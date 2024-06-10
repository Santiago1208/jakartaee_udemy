package org.srestrepo.apiservlet.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import org.srestrepo.apiservlet.webapp.headers.models.Product;
import org.srestrepo.apiservlet.webapp.headers.services.ProductService;
import org.srestrepo.apiservlet.webapp.headers.services.ProductServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/products.json")
public class ProductJSONServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductJSONServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.getProducts();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(products);
            response.setContentType(MediaType.APPLICATION_JSON);
            response.getWriter().write(json);
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "doGet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletInputStream jsonStream = request.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(jsonStream, Product.class);
            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.println("<!DOCTYPE html>");
                printWriter.println("<html>");
                printWriter.println("    <head>");
                printWriter.println("        <meta charset=\"UTF-8\">");
                printWriter.println("        <title>Product Detail coming from JSON</title>");
                printWriter.println("    </head>");
                printWriter.println("    <body>");
                printWriter.println("        <h1>Product Detail coming from JSON</h1>");
                printWriter.println("        <ul>");
                printWriter.println("        <li>" + product.getId() + "</li>");
                printWriter.println("        <li>" + product.getName() + "</li>");
                printWriter.println("        <li>" + product.getType() + "</li>");
                printWriter.println("        <li>" + product.getPrice() + "</li>");
                printWriter.println("        </ul>");
                printWriter.println("    </body>");
                printWriter.println("</html>");
            } catch (IOException e) {
                log.throwing(this.getClass().getName(), "doPost", e);
            }
        } catch (IOException e) {
            log.throwing(this.getClass().getName(), "doPost", e);
        }
    }
}
