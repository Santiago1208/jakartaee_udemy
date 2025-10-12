package org.srestrepo.appejb.remote.service;

import jakarta.ejb.Stateful;
import org.srestrepo.appejb.remote.model.Product;

import java.util.List;

@Stateful
public class ProductStatefulServiceRemoteEJBImpl implements ProductServiceRemoteEJB {

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
