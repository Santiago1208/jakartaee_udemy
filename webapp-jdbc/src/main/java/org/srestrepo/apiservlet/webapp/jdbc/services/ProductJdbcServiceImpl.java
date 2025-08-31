package org.srestrepo.apiservlet.webapp.jdbc.services;

import org.srestrepo.apiservlet.webapp.jdbc.models.Product;
import org.srestrepo.apiservlet.webapp.jdbc.repositories.ProductJdbcRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductJdbcServiceImpl implements ProductService {
    private final ProductJdbcRepositoryImpl productJdbcRepository;

    public ProductJdbcServiceImpl(Connection connection) {
        productJdbcRepository = new ProductJdbcRepositoryImpl(connection);
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
            return Optional.of(productJdbcRepository.findById(id));
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }
}
