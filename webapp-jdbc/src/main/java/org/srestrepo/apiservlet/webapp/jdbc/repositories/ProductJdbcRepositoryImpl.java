package org.srestrepo.apiservlet.webapp.jdbc.repositories;

import org.srestrepo.apiservlet.webapp.jdbc.models.Category;
import org.srestrepo.apiservlet.webapp.jdbc.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductJdbcRepositoryImpl implements JdbcRepository<Product> {
    private final Connection connection;

    public ProductJdbcRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select p.*, c.name as category from products as p inner join categories as c on p.category_id = c.id" )) {
            while (resultSet.next()) {
                Product product = getProduct(resultSet);
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product findById(Long id) throws SQLException {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement("select p.*, c.name as category from products as p inner join categories c on c.id = p.category_id where p.id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = getProduct(resultSet);
                }
            }
        }
        return product;
    }

    @Override
    public void save(Product entity) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    private static Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getInt("price"));
        product.setSku(resultSet.getString("sku"));
        product.setCreatedAt(resultSet.getDate("created_at").toLocalDate());

        Category category = new Category();
        category.setId(resultSet.getLong("category_id"));
        category.setName(resultSet.getString("category"));
        product.setCategory(category);

        return product;
    }
}
