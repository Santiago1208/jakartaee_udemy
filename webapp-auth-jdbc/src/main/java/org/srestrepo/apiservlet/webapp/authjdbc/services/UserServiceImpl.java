package org.srestrepo.apiservlet.webapp.authjdbc.services;

import org.srestrepo.apiservlet.webapp.authjdbc.models.User;
import org.srestrepo.apiservlet.webapp.authjdbc.repositories.UserJdbcRepository;
import org.srestrepo.apiservlet.webapp.authjdbc.repositories.UserJdbcRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
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
}
