package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Local;
import org.srestrepo.webapp.ejb.model.Product;

import java.util.List;

@Local
public interface ProductServiceLocalEJB {
    List<Product> findAll();
    Product save(Product product);
}
