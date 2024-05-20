package org.srestrepo.java.jdbc;

import org.srestrepo.java.jdbc.dao.GenericDAO;
import org.srestrepo.java.jdbc.dao.ProductDAO;
import org.srestrepo.java.jdbc.model.Category;
import org.srestrepo.java.jdbc.model.Product;
import org.srestrepo.java.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class JdbcExample {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance()) {
            GenericDAO<Product> productDAO = new ProductDAO();

            // Find All
            System.out.println("============ Find All ============");
            productDAO.findAll().forEach(System.out::println);

            // Find by ID
            System.out.println("============ Find By ID ============");
            System.out.println(productDAO.findById(2L));

            // Save
            System.out.println("============ Save Product ============");
            Product newProduct = new Product();
            newProduct.setName("Razer Keyboard");
            newProduct.setPrice(450);
            newProduct.setRegisterDate(new Date());

            Category technology = new Category();
            technology.setId(3L);
            newProduct.setCategory(technology);

            productDAO.save(newProduct);
            System.out.println("Product Saved successfully!");

            // Find All - the saved product
            System.out.println("============ Find All - the Saved Product ============");
            productDAO.findAll().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
