package org.srestrepo.apiservlet.webapp.authjdbc.services;

import org.srestrepo.apiservlet.webapp.authjdbc.models.Category;
import org.srestrepo.apiservlet.webapp.authjdbc.models.Product;

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

    @Override
    public void save(Product product) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Category> getCategories() {
        return List.of();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsBySku(String sku) {
        return false;
    }
}
