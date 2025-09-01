package org.srestrepo.apiservlet.webapp.jstl.services;

import org.srestrepo.apiservlet.webapp.jstl.models.Category;
import org.srestrepo.apiservlet.webapp.jstl.models.Product;
import org.srestrepo.apiservlet.webapp.jstl.repositories.CategoryJdbcRepositoryImpl;
import org.srestrepo.apiservlet.webapp.jstl.repositories.JdbcRepository;
import org.srestrepo.apiservlet.webapp.jstl.repositories.ProductJdbcRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductJdbcServiceImpl implements ProductService {
    private final JdbcRepository<Product> productJdbcRepository;
    private final JdbcRepository<Category> categoryJdbcRepository;

    public ProductJdbcServiceImpl(Connection connection) {
        productJdbcRepository = new ProductJdbcRepositoryImpl(connection);
        categoryJdbcRepository = new CategoryJdbcRepositoryImpl(connection);
    }

    @Override
    public List<Product> getProducts() {
        try {
            return productJdbcRepository.findAll();
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(productJdbcRepository.findById(id));
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Product product) {
        try {
            productJdbcRepository.save(product);
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productJdbcRepository.delete(id);
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Category> getCategories() {
        try {
            return categoryJdbcRepository.findAll();
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.ofNullable(categoryJdbcRepository.findById(id));
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean existsBySku(String sku) {
        try {
            return ((ProductJdbcRepositoryImpl) productJdbcRepository).existsBySku(sku);
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }
}
