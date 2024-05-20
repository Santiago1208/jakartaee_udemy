package org.srestrepo.java.jdbc;

import org.srestrepo.java.jdbc.dao.GenericDAO;
import org.srestrepo.java.jdbc.dao.ProductDAO;
import org.srestrepo.java.jdbc.model.Product;
import org.srestrepo.java.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUpdateExample {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance()) {
            GenericDAO<Product> productDAO = new ProductDAO();

            // Find All
            System.out.println("============ Find All ============");
            productDAO.findAll().forEach(System.out::println);

            // Find by ID
            System.out.println("============ Find By ID ============");
            System.out.println(productDAO.findById(2L));

            // Update
            System.out.println("============ Update Product ============");
            Product newProduct = new Product();
            newProduct.setId(33L);
            newProduct.setName("Razer Keyboard");
            newProduct.setPrice(700);
            productDAO.save(newProduct);
            System.out.println("Product Deleted successfully!");

            // Find All - the saved product
            System.out.println("============ Find All - the Updated Product ============");
            productDAO.findAll().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
