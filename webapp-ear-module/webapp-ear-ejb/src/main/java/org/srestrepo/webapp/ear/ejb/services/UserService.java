package org.srestrepo.webapp.ear.ejb.services;

import jakarta.ejb.Local;
import org.srestrepo.webapp.ear.ejb.entities.User;

import java.util.List;

@Local
public interface UserService {
    List<User> findAll();
}
