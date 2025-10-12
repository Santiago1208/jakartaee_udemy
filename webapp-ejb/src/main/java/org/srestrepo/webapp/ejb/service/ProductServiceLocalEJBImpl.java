package org.srestrepo.webapp.ejb.service;

import jakarta.ejb.Stateless;
import org.srestrepo.webapp.ejb.model.Product;

import java.util.List;

@Stateless
public class ProductServiceLocalEJBImpl implements ProductServiceLocalEJB {

    @Override
    public List<Product> findAll() {
        return List.of(
                new Product("Peras"),
                new Product("Manzanas"),
                new Product("Naranjas")
        );
    }

    @Override
    public Product save(Product product) {
        System.out.println("Saving product: " + product);
        Product p = new Product();
        p.setName(product.getName());
        return p;
    }
}
