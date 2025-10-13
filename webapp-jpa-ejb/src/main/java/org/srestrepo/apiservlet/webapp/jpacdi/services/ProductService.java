package org.srestrepo.apiservlet.webapp.jpacdi.services;

import jakarta.ejb.Local;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Category;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Product;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {
    List<Product> getProducts();
    Optional<Product> findById(Long id);
    void save(Product product);
    void delete(Long id);
    List<Category> getCategories();
    Optional<Category> findCategoryById(Long id);
    boolean existsBySku(String sku);
}
