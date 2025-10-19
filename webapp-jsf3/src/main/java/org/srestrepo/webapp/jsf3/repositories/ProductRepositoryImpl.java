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
        return em.createQuery("select p from Product p left outer join fetch p.category", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return em.createQuery("select p from Product p left outer join fetch p.category where p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Product entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
    }

    @Override
    public void delete(Long id) {
        em.remove(findById(id));
    }
}
