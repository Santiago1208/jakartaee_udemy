package org.srestrepo.apiservlet.webapp.authjdbc.services;

import org.srestrepo.apiservlet.webapp.authjdbc.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
}
