package org.srestrepo.java.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    List<T> findAll() throws SQLException;
    T findById(Long id) throws SQLException;
    T save(T model) throws SQLException;
    void deleteById(Long id) throws SQLException;
}
