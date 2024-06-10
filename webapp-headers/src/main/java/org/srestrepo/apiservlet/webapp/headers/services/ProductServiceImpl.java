package org.srestrepo.apiservlet.webapp.headers.services;

import org.srestrepo.apiservlet.webapp.headers.models.Product;

import java.util.Arrays;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> getProducts() {
        return Arrays.asList(new Product(1L, "Notebook", "Computing", 175_000),
                new Product(2L, "Desktop", "Office", 100_000),
                new Product(3L, "Keyboard", "Computing", 40_000));
    }
}
