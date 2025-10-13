package org.srestrepo.webapp.ear.ejb.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.srestrepo.webapp.ear.ejb.entities.User;

import java.util.List;

@RequestScoped
public class UserRepositoryImpl implements UserRepository {

    @Inject
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("from User", User.class).getResultList();
    }
}
