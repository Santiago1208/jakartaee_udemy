package org.srestrepo.java.jdbc.dao;

import org.srestrepo.java.jdbc.model.Category;
import org.srestrepo.java.jdbc.model.Product;
import org.srestrepo.java.jdbc.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements GenericDAO<Product> {

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet result = statement.executeQuery("SELECT p.*, c.NAME AS category FROM PRODUCTS " +
                     "AS p INNER JOIN CATEGORIES AS c ON (p.category_id = c.id)")) {
            while (result.next()) {
                Product product = createProduct(result);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Product product = null;
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT p.*, c.NAME AS category FROM PRODUCTS " +
                        "AS p INNER JOIN CATEGORIES AS c ON (p.category_id = c.id) WHERE p.ID = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    product = createProduct(result);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Product product) {
        String sql;
        if (product.getId() != null && product.getId() > 0L) {
            sql = "UPDATE PRODUCTS SET NAME = ?, PRICE = ?, CATEGORY_ID = ? WHERE ID = ?";
        } else {
            sql = "INSERT INTO PRODUCTS (NAME, PRICE, CATEGORY_ID, REGISTER_DATE) VALUES (?, ?, ?, ?)";
        }
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getCategory().getId());
            if (product.getId() != null && product.getId() > 0) {
                preparedStatement.setLong(4, product.getId());
            } else {
                preparedStatement.setDate(4, new Date(product.getRegisterDate().getTime()));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("DELETE FROM PRODUCTS WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product createProduct(ResultSet result) throws SQLException {
        Product product = new Product();
        product.setId(result.getLong("id"));
        product.setName(result.getString("name"));
        product.setPrice(result.getInt("price"));
        product.setRegisterDate(result.getDate("register_date"));

        Category category = new Category();
        category.setId(result.getLong("category_id"));
        category.setName(result.getString("category"));
        product.setCategory(category);

        return product;
    }
}
