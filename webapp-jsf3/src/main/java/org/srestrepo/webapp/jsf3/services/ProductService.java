package org.srestrepo.webapp.jsf3.services;

import jakarta.ejb.Local;
import org.srestrepo.webapp.jsf3.entities.Product;

import java.util.List;
import java.util.Optional;

@Local
public interface ProductService {
    List<Product> getProducts();
    Optional<Product> getProduct(Long id);
    void saveProduct(Product product);
    void deleteProduct(Long id);
}
