package org.srestrepo.webapp.jsf3.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.srestrepo.webapp.jsf3.entities.Product;

import java.util.List;

@RequestScoped
public class ProductRepositoryImpl implements CrudRepository<Product> {

    @Inject
    private EntityManager em;

    @Override
    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }
}
