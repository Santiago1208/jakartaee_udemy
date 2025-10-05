package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Repository;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Category;

import java.util.List;

@Repository
public class CategoryJpaRepositoryImpl implements CrudRepository<Category> {
    @Inject
    private EntityManager em;

    @Override
    public List<Category> findAll() throws Exception {
        return em.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category findById(Long id) throws Exception {
        return em.find(Category.class, id);
    }

    @Override
    public void save(Category entity) throws Exception {
        if (entity.getId() != null && entity.getId() > 0L) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Category category = findById(id);
        em.remove(category);
    }
}
