package org.srestrepo.apiservlet.webapp.jpacdi.services;

import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.jpacdi.config.DefaultProductService;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Service;
import org.srestrepo.apiservlet.webapp.jpacdi.interceptors.JpaTransactional;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Category;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Product;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.CrudRepository;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.JpaRepository;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.ProductJdbcRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Service
@DefaultProductService
@JpaTransactional
public class ProductServiceImpl implements ProductService {

    @Inject
    @JpaRepository
    private CrudRepository<Product> productJdbcRepository;
    @Inject
    @JpaRepository
    private CrudRepository<Category> categoryJdbcRepository;

    @Override
    public List<Product> getProducts() {
        try {
            return productJdbcRepository.findAll();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(productJdbcRepository.findById(id));
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Product product) {
        try {
            productJdbcRepository.save(product);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productJdbcRepository.delete(id);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Category> getCategories() {
        try {
            return categoryJdbcRepository.findAll();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.ofNullable(categoryJdbcRepository.findById(id));
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean existsBySku(String sku) {
        try {
            return ((ProductJdbcRepositoryImpl) productJdbcRepository).existsBySku(sku);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }
}
