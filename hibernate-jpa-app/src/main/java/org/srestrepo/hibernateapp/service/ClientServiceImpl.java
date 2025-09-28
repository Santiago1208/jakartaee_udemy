package org.srestrepo.hibernateapp.service;

import jakarta.persistence.EntityManager;
import org.srestrepo.hibernateapp.entity.Client;
import org.srestrepo.hibernateapp.repository.ClientRepository;
import org.srestrepo.hibernateapp.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final EntityManager em;
    private final CrudRepository<Client> clientRepository;

    public ClientServiceImpl(EntityManager em) {
        this.clientRepository = new ClientRepository(em);
        this.em = em;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.ofNullable(clientRepository.findById(id));
    }

    @Override
    public void save(Client client) {
        try {
            em.getTransaction().begin();
            clientRepository.save(client);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            em.getTransaction().begin();
            clientRepository.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
