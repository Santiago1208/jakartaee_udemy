package org.srestrepo.webapp.jsf3.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.srestrepo.webapp.jsf3.entities.Product;
import org.srestrepo.webapp.jsf3.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class ProductServiceImpl implements ProductService {

    @Inject
    private CrudRepository<Product> productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return Optional.ofNullable(productRepository.findById(id));
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
