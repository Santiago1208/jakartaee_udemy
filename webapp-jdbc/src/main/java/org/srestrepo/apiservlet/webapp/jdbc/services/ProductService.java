package org.srestrepo.apiservlet.webapp.jdbc.services;

import org.srestrepo.apiservlet.webapp.jdbc.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> findById(Long id);

}
