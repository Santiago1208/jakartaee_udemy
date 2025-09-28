package org.srestrepo.hibernateapp.repository;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;

import java.util.List;

public class ClientRepository implements CrudRepository<Client> {

    private final EntityManager em;

    public ClientRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Client> findAll() {
        return em.createQuery("select c from Client c", Client.class).getResultList();
    }

    @Override
    public Client findById(Long id) {
        return em.find(Client.class, id);
    }

    @Override
    public void save(Client entity) {
        if (entity.getId() != null && entity.getId() > 0) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
    }

    @Override
    public void delete(Long id) {
        Client client = findById(id);
        em.remove(client);
    }
}
