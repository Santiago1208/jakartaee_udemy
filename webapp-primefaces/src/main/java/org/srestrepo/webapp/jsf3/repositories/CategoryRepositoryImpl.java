package org.srestrepo.webapp.jsf3.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.srestrepo.webapp.jsf3.entities.Category;

import java.util.List;

@RequestScoped
public class CategoryRepositoryImpl implements CrudRepository<Category> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class).getResultList();
    }

    @Override
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    @Override
    public void save(Category entity) {
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
