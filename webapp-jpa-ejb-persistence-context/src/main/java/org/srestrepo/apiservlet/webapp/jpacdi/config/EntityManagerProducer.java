package org.srestrepo.apiservlet.webapp.jpacdi.config;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(name = "exampleJPA")
    private EntityManager em;

    @Produces
    @RequestScoped
    private EntityManager produceEntityManager() {
        return em;
    }

}
