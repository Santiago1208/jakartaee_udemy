package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Product;

public interface ProductRepository extends CrudRepository<Product> {
    boolean existsBySku(String sku) throws Exception;
}
