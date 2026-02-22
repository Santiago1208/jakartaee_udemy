package org.srestrepo.webapp.jsf3.repositories;

import org.srestrepo.webapp.jsf3.entities.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product> {
    List<Product> findByName(String name);
}
