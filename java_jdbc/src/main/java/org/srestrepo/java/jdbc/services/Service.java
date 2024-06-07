package org.srestrepo.java.jdbc.services;

import org.srestrepo.java.jdbc.model.Category;
import org.srestrepo.java.jdbc.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<Product> getAllProducts() throws SQLException;
    Product getProductById(Long id) throws SQLException;
    Product saveProduct(Product product) throws SQLException;
    void deleteProductById(Long id) throws SQLException;
    List<Category> getAllCategories() throws SQLException;
    Category getCategoryById(Long id) throws SQLException;
    Category saveCategory(Category category) throws SQLException;
    void deleteCategoryById(Long id) throws SQLException;
    void saveProductWithCategory(Product product, Category category) throws SQLException;
}
