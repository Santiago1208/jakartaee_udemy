package org.srestrepo.apiservlet.webapp.jdbc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.jdbc.models.Category;
import org.srestrepo.apiservlet.webapp.jdbc.models.Product;
import org.srestrepo.apiservlet.webapp.jdbc.services.ProductJdbcServiceImpl;
import org.srestrepo.apiservlet.webapp.jdbc.services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Connection connection = (Connection) req.getAttribute("jdbcConnection");
        ProductService productService = new ProductJdbcServiceImpl(connection);

        String name = req.getParameter("name");

        Integer price;
        try {
            price =  Integer.parseInt(req.getParameter("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }

        String sku =  req.getParameter("sku");

        String createdAtStr = req.getParameter("createdAt");
        LocalDate createdAt = LocalDate.parse(createdAtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Long categoryId;
        try {
            categoryId = Long.parseLong(req.getParameter("category"));
        } catch (NumberFormatException e) {
            categoryId = 0L;
        }


        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setSku(sku);
        product.setCreatedAt(createdAt);

        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        productService.save(product);
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
