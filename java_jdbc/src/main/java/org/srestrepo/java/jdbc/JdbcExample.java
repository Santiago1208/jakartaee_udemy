package org.srestrepo.java.jdbc;

import org.srestrepo.java.jdbc.dao.CategoryDAO;
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
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection.getAutoCommit()) {
                connection.setAutoCommit(false);
            }
            try {
                GenericDAO<Product> productDAO = new ProductDAO(connection);
                GenericDAO<Category> categoryDAO = new CategoryDAO(connection);

                System.out.println("============ Save new Category ============");
                Category appliances = new Category();
                appliances.setName("Appliances");
                appliances = categoryDAO.save(appliances);
                System.out.println("Category Saved successfully: " + appliances.getId());


                // Find All
                System.out.println("============ Find All ============");
                productDAO.findAll().forEach(System.out::println);

                // Find by ID
                System.out.println("============ Find By ID ============");
                System.out.println(productDAO.findById(2L));

                // Save
                System.out.println("============ Save Product ============");
                Product newProduct = new Product();
                newProduct.setName("Blender");
                newProduct.setPrice(1550);
                newProduct.setRegisterDate(new Date());
                newProduct.setSku("sku44");
                newProduct.setCategory(appliances);

                productDAO.save(newProduct);
                System.out.println("Product Saved successfully: " + newProduct.getId());

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
