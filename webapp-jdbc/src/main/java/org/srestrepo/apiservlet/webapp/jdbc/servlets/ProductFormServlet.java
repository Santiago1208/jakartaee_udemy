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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final Map<String, String> errors = new HashMap<>();

        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            errors.put("name", "Please enter a name");
        }

        Integer price;
        try {
            price =  Integer.parseInt(req.getParameter("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }
        if (price.equals(0)) {
            errors.put("price", "Please enter a price");
        }

        String sku =  req.getParameter("sku");
        if (sku == null || sku.isBlank()) {
            errors.put("sku", "Please enter a sku");
        } else if (sku.length() > 10) {
            errors.put("sku", "Sku exceeds maximum 10 characters");
        }
        boolean existsSku = productService.existsBySku(sku);
        if (existsSku) {
            errors.put("sku", "Sku already exists");
        }

        String createdAtStr = req.getParameter("createdAt");
        if (createdAtStr == null || createdAtStr.isBlank()) {
            errors.put("createdAt", "Please enter a creation date");
        }

        Long categoryId;
        try {
            categoryId = Long.parseLong(req.getParameter("category"));
        } catch (NumberFormatException e) {
            categoryId = 0L;
        }
        if (categoryId.equals(0L)) {
            errors.put("category", "Please enter a category");
        }

        if (errors.isEmpty()) {
            LocalDate createdAt = LocalDate.parse(createdAtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
        } else {
            req.setAttribute("errors", errors);
            doGet(req, resp);
        }
    }
}
