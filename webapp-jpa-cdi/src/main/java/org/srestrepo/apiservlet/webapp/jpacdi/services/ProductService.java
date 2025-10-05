package org.srestrepo.apiservlet.webapp.jpacdi.services;

import org.srestrepo.apiservlet.webapp.jpacdi.models.Category;
import org.srestrepo.apiservlet.webapp.jpacdi.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);
    List<Category> getCategories();
    Optional<Category> findCategoryById(Long id);
    boolean existsBySku(String sku);
}
