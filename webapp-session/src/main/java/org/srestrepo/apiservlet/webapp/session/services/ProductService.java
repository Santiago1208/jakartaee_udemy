package org.srestrepo.apiservlet.webapp.session.services;

import org.srestrepo.apiservlet.webapp.session.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();
    Optional<Product> findById(Long id);

}
