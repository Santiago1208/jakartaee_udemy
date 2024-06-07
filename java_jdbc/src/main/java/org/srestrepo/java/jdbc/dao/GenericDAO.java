package org.srestrepo.java.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    T save(T model) throws SQLException;
    void deleteById(Long id) throws SQLException;

    default Long retrieveLastEntityId(PreparedStatement preparedStatement) throws SQLException {
        long lastEntityId = 0L;
        try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                lastEntityId = resultSet.getLong(1);
            }
        }
        return lastEntityId;
    }
}
