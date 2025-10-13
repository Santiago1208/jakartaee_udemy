package org.srestrepo.webapp.ear.ejb.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.srestrepo.webapp.ear.ejb.entities.User;
import org.srestrepo.webapp.ear.ejb.repositories.UserRepository;

import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
