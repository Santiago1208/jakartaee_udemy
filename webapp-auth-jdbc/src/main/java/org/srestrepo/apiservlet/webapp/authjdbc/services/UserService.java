package org.srestrepo.apiservlet.webapp.authjdbc.services;

import org.srestrepo.apiservlet.webapp.authjdbc.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
    List<User> findAll();
    Optional<User> findById(Long id);
    void save(User user);
    void delete(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
