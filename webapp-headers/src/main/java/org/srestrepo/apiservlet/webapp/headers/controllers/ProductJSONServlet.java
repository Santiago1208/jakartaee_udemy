package org.srestrepo.apiservlet.webapp.headers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MediaType;
import org.srestrepo.apiservlet.webapp.headers.models.Product;
import org.srestrepo.apiservlet.webapp.headers.services.ProductService;
import org.srestrepo.apiservlet.webapp.headers.services.ProductServiceImpl;

import java.io.IOException;
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
}
