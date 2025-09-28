package org.srestrepo.hibernateapp.service;

import org.srestrepo.hibernateapp.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Optional<Client> findById(Long id);
    void save(Client client);
    void delete(Long id);
}
