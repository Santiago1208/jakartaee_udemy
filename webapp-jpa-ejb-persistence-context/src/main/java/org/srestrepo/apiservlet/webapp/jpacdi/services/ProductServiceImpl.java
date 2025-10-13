package org.srestrepo.apiservlet.webapp.jpacdi.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.jpacdi.config.DefaultProductService;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Service;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Category;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Product;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.CrudRepository;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.JpaRepository;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@DefaultProductService
@Stateless
public class ProductServiceImpl implements ProductService {

    @Inject
    @JpaRepository
    private ProductRepository productRepository;
    @Inject
    @JpaRepository
    private CrudRepository<Category> categoryRepository;

    @Override
    public List<Product> getProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            return Optional.ofNullable(productRepository.findById(id));
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void save(Product product) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            productRepository.delete(id);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Category> getCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        try {
            return Optional.ofNullable(categoryRepository.findById(id));
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean existsBySku(String sku) {
        try {
            return productRepository.existsBySku(sku);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e);
        }
    }
}
