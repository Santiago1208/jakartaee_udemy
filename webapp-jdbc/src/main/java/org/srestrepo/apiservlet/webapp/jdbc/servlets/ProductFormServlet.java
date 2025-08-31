package org.srestrepo.apiservlet.webapp.jdbc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jdbc.models.Category;
import org.srestrepo.apiservlet.webapp.jdbc.services.ProductJdbcServiceImpl;
import org.srestrepo.apiservlet.webapp.jdbc.services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/product/add")
public class ProductFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = (Connection) req.getAttribute("jdbcConnection");
        ProductService productService = new ProductJdbcServiceImpl(connection);
        List<Category> categories = productService.getCategories();
        req.setAttribute("categories", categories);

        getServletContext().getRequestDispatcher("/product-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
