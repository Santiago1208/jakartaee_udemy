package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll() throws Exception;
    T findById(Long id) throws Exception;
    void save(T entity) throws Exception;
    void delete(Long id) throws Exception;
}
