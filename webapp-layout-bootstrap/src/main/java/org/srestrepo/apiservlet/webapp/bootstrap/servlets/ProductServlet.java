package org.srestrepo.apiservlet.webapp.bootstrap.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.bootstrap.models.Product;
import org.srestrepo.apiservlet.webapp.bootstrap.services.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet({"/products.html", "/products"})
public class ProductServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ProductServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = (Connection) request.getAttribute("jdbcConnection");
        ProductService productService = new ProductJdbcServiceImpl(connection);
        List<Product> products = productService.getProducts();

        LoginService loginService = new LoginSessionService();
        Optional<String> usernameOptional = loginService.getUsername(request);

        request.setAttribute("products", products);
        request.setAttribute("username", usernameOptional);
        getServletContext().getRequestDispatcher("/view-products.jsp").forward(request, response);
    }
}
