package org.srestrepo.java.jdbc;

import org.srestrepo.java.jdbc.dao.GenericDAO;
import org.srestrepo.java.jdbc.dao.ProductDAO;
import org.srestrepo.java.jdbc.model.Product;
import org.srestrepo.java.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDeleteExample {

    public static void main(String[] args) throws SQLException {
        try (Connection connection = DatabaseConnection.getInstance()) {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                GenericDAO<Product> productDAO = new ProductDAO();

                // Find All
                System.out.println("============ Find All ============");
                productDAO.findAll().forEach(System.out::println);

                // Find by ID
                System.out.println("============ Find By ID ============");
                System.out.println(productDAO.findById(2L));

                // Delete
                System.out.println("============ Delete Product ============");
                productDAO.deleteById(33L);
                System.out.println("Product Updated successfully!");

                // Find All - the saved product
                System.out.println("============ Find All - the Deleted Product ============");
                productDAO.findAll().forEach(System.out::println);

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
    }
}
