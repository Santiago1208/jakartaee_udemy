package org.srestrepo.webapp.jsf3.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.srestrepo.webapp.jsf3.entities.Category;
import org.srestrepo.webapp.jsf3.entities.Product;
import org.srestrepo.webapp.jsf3.repositories.CrudRepository;
import org.srestrepo.webapp.jsf3.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CrudRepository<Category> categoryRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return Optional.ofNullable(productRepository.findById(id));
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return Optional.ofNullable(categoryRepository.findById(id));
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByName(name);
    }
}
