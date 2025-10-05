package org.srestrepo.apiservlet.webapp.jpacdi.services;

import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.jpacdi.config.DefaultProductService;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Service;
import org.srestrepo.apiservlet.webapp.jpacdi.models.Category;
import org.srestrepo.apiservlet.webapp.jpacdi.models.Product;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.JdbcRepository;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.ProductJdbcRepositoryImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@DefaultProductService
public class ProductJdbcServiceImpl implements ProductService {

    @Inject
    private JdbcRepository<Product> productJdbcRepository;
    @Inject
    private JdbcRepository<Category> categoryJdbcRepository;

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
