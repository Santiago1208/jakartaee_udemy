package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import java.sql.SQLException;
import java.util.List;

public interface JdbcRepository<T> {
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    void save(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
}
