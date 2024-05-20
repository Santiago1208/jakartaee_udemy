package org.srestrepo.java.jdbc.dao;

import org.srestrepo.java.jdbc.model.Product;
import java.util.List;

public class ProductDAO implements GenericDAO<Product> {
    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void save(Product model) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
