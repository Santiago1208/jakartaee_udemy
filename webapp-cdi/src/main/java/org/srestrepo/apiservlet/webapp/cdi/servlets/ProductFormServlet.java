package org.srestrepo.apiservlet.webapp.cdi.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.srestrepo.apiservlet.webapp.cdi.config.DefaultProductService;
import org.srestrepo.apiservlet.webapp.cdi.models.Category;
import org.srestrepo.apiservlet.webapp.cdi.models.Product;
import org.srestrepo.apiservlet.webapp.cdi.services.ProductService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/product/add")
public class ProductFormServlet extends HttpServlet {

    @Inject
    @DefaultProductService
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = productService.getCategories();

        Long productId;
        try {
            productId = Long.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            productId = 0L;
        }
        Product product = new Product();
        product.setCategory(new Category());
        if (productId > 0L) {
            Optional<Product> optionalProduct = productService.findById(productId);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
            }
        }

        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        req.setAttribute("title", req.getAttribute("title") + ": Product Form");
        getServletContext().getRequestDispatcher("/product-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        LocalDate createdAt;
        try {
            createdAt = LocalDate.parse(createdAtStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            createdAt = null;
        }

        long productId;
        try {
            productId = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
            productId = 0L;
        }
        if (productId == 0L) {
            boolean existsSku = productService.existsBySku(sku);
            if (existsSku) {
                errors.put("sku", "Sku already exists");
            }
        }

        Product product = new Product();
        product.setId(productId);
        product.setName(name);
        product.setPrice(price);
        product.setSku(sku);
        product.setCreatedAt(createdAt);

        Category category = new Category();
        category.setId(categoryId);
        product.setCategory(category);

        if (errors.isEmpty()) {
            productService.save(product);
            resp.sendRedirect(req.getContextPath() + "/products");
        } else {
            List<Category> categories = productService.getCategories();
            req.setAttribute("errors", errors);
            req.setAttribute("product", product);
            req.setAttribute("categories", categories);
            req.setAttribute("title", req.getAttribute("title") + ": Products");
            getServletContext().getRequestDispatcher("/product-form.jsp").forward(req, resp);
        }
    }
}
