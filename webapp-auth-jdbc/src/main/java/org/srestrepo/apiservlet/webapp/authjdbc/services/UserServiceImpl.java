package org.srestrepo.apiservlet.webapp.authjdbc.services;

import org.srestrepo.apiservlet.webapp.authjdbc.models.User;
import org.srestrepo.apiservlet.webapp.authjdbc.repositories.UserJdbcRepository;
import org.srestrepo.apiservlet.webapp.authjdbc.repositories.UserJdbcRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserJdbcRepository userJdbcRepository;

    public UserServiceImpl(Connection connection) {
        this.userJdbcRepository = new UserJdbcRepositoryImpl(connection);
    }

    @Override
    public Optional<User> login(String username, String password) {
        try {
            return Optional.ofNullable(userJdbcRepository.findByUsername(username))
                    .filter(user -> user.getPassword().equals(password));
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userJdbcRepository.findAll();
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(userJdbcRepository.findById(id));
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(User user) {
        try {
            userJdbcRepository.save(user);
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userJdbcRepository.delete(id);
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        try {
            return Optional.ofNullable(userJdbcRepository.findByUsername(username)).isPresent();
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return Optional.ofNullable(userJdbcRepository.findByEmail(email)).isPresent();
        } catch (SQLException e) {
            throw new JdbcServiceException(e.getMessage(), e.getCause());
        }
    }
}
