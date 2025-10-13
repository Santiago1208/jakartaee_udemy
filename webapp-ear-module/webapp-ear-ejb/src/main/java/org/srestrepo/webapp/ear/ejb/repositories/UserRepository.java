package org.srestrepo.webapp.ear.ejb.repositories;

import org.srestrepo.webapp.ear.ejb.entities.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
}
