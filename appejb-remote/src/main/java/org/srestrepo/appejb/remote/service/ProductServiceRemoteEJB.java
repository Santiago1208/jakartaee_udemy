package org.srestrepo.appejb.remote.service;

import jakarta.ejb.Remote;
import org.srestrepo.appejb.remote.model.Product;

import java.util.List;

@Remote
public interface ProductServiceRemoteEJB {
    List<Product> findAll();
    Product save(Product product);
}
