package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Repository;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.User;

import java.util.List;

@Repository
@JpaRepository
public class UserJpaRepositoryImpl implements UserRepository {
    @Inject
    private EntityManager em;

    @Override
    public User findByUsername(String username) throws Exception {
        return em.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User findByEmail(String email) throws Exception {
        return em.createQuery("select u from User u where u.email=:email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() throws Exception {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(Long id) throws Exception {
        return em.find(User.class, id);
    }

    @Override
    public void save(User entity) throws Exception {
        if (entity.getId() != null && entity.getId() > 0L) {
            em.merge(entity);
        }  else {
            em.persist(entity);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        em.remove(findById(id));
    }
}
