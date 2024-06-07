package org.srestrepo.java.jdbc.services;

import org.srestrepo.java.jdbc.dao.CategoryDAO;
import org.srestrepo.java.jdbc.dao.GenericDAO;
import org.srestrepo.java.jdbc.dao.ProductDAO;
import org.srestrepo.java.jdbc.model.Category;
import org.srestrepo.java.jdbc.model.Product;
import org.srestrepo.java.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CatalogService implements Service {

    private final GenericDAO<Category> categoryDAO;
    private final GenericDAO<Product> productDAO;

    public CatalogService() {
        this.categoryDAO = new CategoryDAO();
        this.productDAO = new ProductDAO();
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            productDAO.setConnection(connection);
            return productDAO.findAll();
        }
    }

    @Override
    public Product getProductById(Long id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            productDAO.setConnection(connection);
            return productDAO.findById(id);
        }
    }

    @Override
    public Product saveProduct(Product product) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            productDAO.setConnection(connection);
            try {
                product = productDAO.save(product);
            } catch (Exception e) {
                connection.rollback();
            }
            connection.commit();
            return product;
        }
    }

    @Override
    public void deleteProductById(Long id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            productDAO.setConnection(connection);
            try {
                productDAO.deleteById(id);
            } catch (Exception e) {
                connection.rollback();
            }
            connection.commit();
        }
    }

    @Override
    public List<Category> getAllCategories() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            categoryDAO.setConnection(connection);
            return categoryDAO.findAll();
        }
    }

    @Override
    public Category getCategoryById(Long id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            categoryDAO.setConnection(connection);
            return categoryDAO.findById(id);
        }
    }

    @Override
    public Category saveCategory(Category category) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            categoryDAO.setConnection(connection);
            try {
                category = categoryDAO.save(category);
            } catch (Exception e) {
                connection.rollback();
            }
            connection.commit();
            return category;
        }
    }

    @Override
    public void deleteCategoryById(Long id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            categoryDAO.setConnection(connection);
            try {
                categoryDAO.deleteById(id);
            } catch (Exception e) {
                connection.rollback();
            }
            connection.commit();
        }
    }

    @Override
    public void saveProductWithCategory(Product product, Category category) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            productDAO.setConnection(connection);
            categoryDAO.setConnection(connection);
            try {
                category = categoryDAO.save(category);
                product.setCategory(category);
                productDAO.save(product);
            } catch (Exception e) {
                connection.rollback();
            }
            connection.commit();
        }
    }
}
