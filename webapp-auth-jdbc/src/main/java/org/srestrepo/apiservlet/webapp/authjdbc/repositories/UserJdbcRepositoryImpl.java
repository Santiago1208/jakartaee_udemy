package org.srestrepo.apiservlet.webapp.authjdbc.repositories;

import org.srestrepo.apiservlet.webapp.authjdbc.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcRepositoryImpl implements UserJdbcRepository {
    private final Connection connection;

    public UserJdbcRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = getUser(rs);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User findById(Long id) throws SQLException {
        User user = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = getUser(rs);
                }
            }
        }
        return user;
    }

    @Override
    public void save(User entity) throws SQLException {
        String sql;
        boolean isUpdate = entity.getId() != null && entity.getId() > 0;
        if (isUpdate) {
            sql = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        }
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getEmail());
            if (isUpdate) {
                ps.setLong(4, entity.getId());
            }
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            }
        }
        return user;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}
