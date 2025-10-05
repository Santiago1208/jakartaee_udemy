package org.srestrepo.apiservlet.webapp.jpacdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jpacdi.config.DefaultProductService;
import org.srestrepo.apiservlet.webapp.jpacdi.models.Product;
import org.srestrepo.apiservlet.webapp.jpacdi.services.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/products.html", "/products"})
public class ProductServlet extends HttpServlet {

    @Inject
    @DefaultProductService
    private ProductService productService;
    @Inject
    private LoginService loginService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getProducts();

        Optional<String> usernameOptional = loginService.getUsername(request);

        request.setAttribute("products", products);
        request.setAttribute("username", usernameOptional);
        request.setAttribute("title", request.getAttribute("title") + ": Products");
        getServletContext().getRequestDispatcher("/view-products.jsp").forward(request, response);
    }
}
