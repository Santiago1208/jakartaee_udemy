package org.srestrepo.apiservlet.webapp.jpacdi.repositories;

import jakarta.inject.Inject;
import org.srestrepo.apiservlet.webapp.jpacdi.config.PostgreSQLConnection;
import org.srestrepo.apiservlet.webapp.jpacdi.config.Repository;
import org.srestrepo.apiservlet.webapp.jpacdi.models.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@JdbcRepository
public class CategoryJdbcRepositoryImpl implements CrudRepository<Category> {

    private final Connection connection;

    @Inject
    public CategoryJdbcRepositoryImpl(@PostgreSQLConnection Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from categories as c")) {
            while (resultSet.next()) {
                Category category = getCategory(resultSet);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public Category findById(Long id) throws SQLException {
        Category category = null;
        try (PreparedStatement statement = connection.prepareStatement("select * from categories as c where c.id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = getCategory(resultSet);
                }
            }
        }
        return category;
    }

    @Override
    public void save(Category entity) throws SQLException {
        String sql;
        boolean isUpdate = entity.getId() != null && entity.getId() > 0;
        if (isUpdate) {
            sql = "update categories set name = ? where id = ?";
        } else {
            sql = "insert into categories (name) values (?)";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            if (isUpdate) {
                statement.setLong(2, entity.getId());
            }
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "delete from categories where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private static Category getCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
