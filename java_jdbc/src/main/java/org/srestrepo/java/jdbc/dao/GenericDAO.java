package org.srestrepo.java.jdbc.dao;

import java.util.List;

public interface GenericDAO<T> {
    List<T> findAll();
    T findById(Long id);
    void save(T model);
    void deleteById(Long id);
}
