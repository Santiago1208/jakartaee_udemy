package org.srestrepo.java.jdbc;

import org.srestrepo.java.jdbc.model.Category;
import org.srestrepo.java.jdbc.model.Product;
import org.srestrepo.java.jdbc.services.CatalogService;
import org.srestrepo.java.jdbc.services.Service;

import java.sql.SQLException;
import java.util.Date;

public class JdbcExample {

    public static void main(String[] args) throws SQLException {
        Service service = new CatalogService();

        // Find All
        log.info("============ Find All ============");
        service.getAllProducts().forEach(product -> log.info(product.toString()));

        Category lights = new Category();
        lights.setName("Lights");

        // Save
        log.info("============ Save Product ============");
        Product newProduct = new Product();
        newProduct.setName("LED Light");
        newProduct.setPrice(990);
        newProduct.setRegisterDate(new Date());
        newProduct.setSku("sku64");

        service.saveProductWithCategory(newProduct, lights);

        // Find All
        log.info("============ Find All ============");
        service.getAllProducts().forEach(product -> log.info(product.toString()));

    }
}
