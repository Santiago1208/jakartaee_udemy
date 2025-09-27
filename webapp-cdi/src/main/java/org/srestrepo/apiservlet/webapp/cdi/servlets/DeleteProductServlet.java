package org.srestrepo.apiservlet.webapp.cdi.servlets;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cdi.models.Product;
import org.srestrepo.apiservlet.webapp.cdi.services.ProductJdbcServiceImpl;
import org.srestrepo.apiservlet.webapp.cdi.services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/product/delete")
public class DeleteProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }  catch (NumberFormatException e) {
            id = 0L;
        }
        if (id > 0L) {
            Optional<Product> product = productService.findById(id);
            if (product.isPresent()) {
                productService.delete(id);
                resp.sendRedirect(req.getContextPath() + "/products");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "The product with id " + id + " was not found");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "The id is required");
        }

    }
}
