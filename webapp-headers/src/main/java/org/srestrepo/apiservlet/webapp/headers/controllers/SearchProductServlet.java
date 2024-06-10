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
import java.util.Optional;
import java.util.logging.Logger;


@WebServlet("/search-product")
public class SearchProductServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(SearchProductServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProductService productService = new ProductServiceImpl();
        String productName = request.getParameter("product");

        if (productName == null || productName.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<Product> productOptional = productService.getProducts()
                .stream()
                .filter(product -> product.getName().startsWith(productName))
                .findFirst();

        if (productOptional.isPresent()) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.println("<!DOCTYPE html>");
                printWriter.println("<html>");
                printWriter.println("    <head>");
                printWriter.println("        <meta charset=\"UTF-8\">");
                printWriter.println("        <title>Found Product</title>");
                printWriter.println("    </head>");
                printWriter.println("    <body>");
                printWriter.println("        <h1>Found Product</h1>");
                printWriter.println("        <h3>The product you are looking for is " + productOptional.get().getName() + "</h3>");
                printWriter.println("    </body>");
                printWriter.println("</html>");
            } catch (IOException e) {
                log.throwing(this.getClass().getName(), "", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
        }
    }
}
