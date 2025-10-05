package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Repository;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Product;

import java.util.List;

@Repository
@JpaRepository
public class ProductJpaRepositoryImpl implements CrudRepository<Product> {
    @Inject
    private EntityManager em;

    @Override
    public List<Product> findAll() throws Exception {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) throws Exception {
        return em.find(Product.class, id);
    }

    @Override
    public void save(Product entity) throws Exception {
        if (entity.getId() != null && entity.getId() > 0L) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Product product = findById(id);
        em.remove(product);
    }
}
