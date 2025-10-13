package org.srestrepo.apiservlet.webapp.jpacdi.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Service;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.User;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.JdbcRepository;
import org.srestrepo.apiservlet.webapp.jpacdi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class UserServiceImpl implements UserService {
    private final UserRepository userJdbcRepository;

    @Inject
    public UserServiceImpl(@JdbcRepository UserRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(userJdbcRepository.findByUsername(username))
                    .filter(user -> user.getPassword().equals(password));
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userJdbcRepository.findAll();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(userJdbcRepository.findById(id));
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(User user) {
        try {
            userJdbcRepository.save(user);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userJdbcRepository.delete(id);
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        try {
            return Optional.ofNullable(userJdbcRepository.findByUsername(username)).isPresent();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return Optional.ofNullable(userJdbcRepository.findByEmail(email)).isPresent();
        } catch (Exception e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }
}
