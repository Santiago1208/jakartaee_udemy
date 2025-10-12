package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Repository;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Product;

import java.util.List;

@Repository
@JpaRepository
public class ProductJpaRepositoryImpl implements ProductRepository {
    @Inject
    private EntityManager em;

    @Override
    public List<Product> findAll() throws Exception {
        return em.createQuery("select p from Product p left outer join fetch p.category", Product.class).getResultList();
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

    @Override
    public boolean existsBySku(String sku) throws Exception {
        return !em.createQuery("select p from Product p where p.sku = :sku", Product.class)
                .setParameter("sku", sku)
                .getResultList().isEmpty();
    }
}
