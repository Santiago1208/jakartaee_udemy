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

                // Save
                System.out.println("============ Save Product ============");
                Product newProduct = new Product();
                newProduct.setName("IBM Keyboard");
                newProduct.setPrice(1550);
                newProduct.setRegisterDate(new Date());
                newProduct.setSku("sku19");

                Category technology = new Category();
                technology.setId(3L);
                newProduct.setCategory(technology);

                productDAO.save(newProduct);
                System.out.println("Product Saved successfully!");

                // Update
                System.out.println("============ Update Product ============");
                newProduct = new Product();
                newProduct.setId(20L);
                newProduct.setName("Corsair K95 Keyboard");
                newProduct.setPrice(700);
                newProduct.setSku("sku20");

                Category entertainment = new Category();
                entertainment.setId(2L);
                newProduct.setCategory(entertainment);

                productDAO.save(newProduct);
                System.out.println("Product Updated successfully!");

                // Find All - the saved product
                System.out.println("============ Find All Again ============");
                productDAO.findAll().forEach(System.out::println);

                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
    }
}
