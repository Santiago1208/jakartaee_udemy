package org.srestrepo.webapp.ear.ejb;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

@ApplicationScoped
public class ResourceProducer {

    @PersistenceUnit(name = "exampleJPA")
    private EntityManagerFactory emf;

    @Produces
    @RequestScoped
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager em) {
        if (em.isOpen()) {
            em.close();
            System.out.println("EntityManager closed");
        }
    }
}
