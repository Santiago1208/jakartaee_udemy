package org.srestrepo.apiservlet.webapp.cdi.repositories;

import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.cdi.config.PostgreSQLConnection;
import org.srestrepo.apiservlet.webapp.cdi.config.Repository;
import org.srestrepo.apiservlet.webapp.cdi.models.Category;
import org.srestrepo.apiservlet.webapp.cdi.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductJdbcRepositoryImpl implements JdbcRepository<Product> {

    @Inject
    @PostgreSQLConnection
    private Connection connection;

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select p.*, c.name as category from products as p inner join categories as c on p.category_id = c.id order by p.id" )) {
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
        String sql;
        boolean isUpdate = entity.getId() != null && entity.getId() > 0;
        if (isUpdate) {
            sql =  "update products set name = ?, price = ?, sku = ?, category_id = ? where id = ?";
        } else {
            sql = "insert into products (name, price, sku, category_id, created_at) values (?, ?, ?, ?, ?)";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPrice());
            statement.setString(3, entity.getSku());
            statement.setLong(4, entity.getCategory().getId());
            if (isUpdate) {
                statement.setLong(5, entity.getId());
            } else {
                statement.setDate(5, Date.valueOf(entity.getCreatedAt()));
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "delete from products where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public boolean existsBySku(String sku) throws SQLException {
        boolean exists = false;
        try (PreparedStatement statement = connection.prepareStatement("select p.id from products as p where sku = ?")) {
            statement.setString(1, sku);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exists = true;
                }
            }
        }
        return exists;
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
