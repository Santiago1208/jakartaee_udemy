package org.srestrepo.apiservlet.webapp.session.services;

import org.srestrepo.apiservlet.webapp.session.models.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getProducts() {
        return Arrays.asList(new Product(1L, "Notebook", "Computing", 175_000),
                new Product(2L, "Desktop", "Office", 100_000),
                new Product(3L, "Keyboard", "Computing", 40_000));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
