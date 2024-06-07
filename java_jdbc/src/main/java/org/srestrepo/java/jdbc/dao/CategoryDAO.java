package org.srestrepo.java.jdbc.dao;

import org.srestrepo.java.jdbc.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements GenericDAO<Category> {

    private final Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM categories")) {
            while (resultSet.next()) {
                Category category = createCategory(resultSet);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public Category findById(Long id) throws SQLException {
        Category category = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    category = createCategory(resultSet);
                }
            }
        }
        return category;
    }

    @Override
    public Category save(Category category) throws SQLException {
        String sql;
        boolean isInserting = category.getId() == null || category.getId() <= 0L;
        boolean isUpdating = category.getId() != null && category.getId() >= 0L;
        if (isInserting) {
            sql = "INSERT INTO categories (name) VALUES (?)";
        } else {
            sql = "UPDATE categories SET name = ? WHERE id = ?";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            if (isUpdating) {
                statement.setLong(2, category.getId());
            }

            statement.executeUpdate();

            if (isInserting) {
                Long lastCategoryId = retrieveLastEntityId(statement);
                category.setId(lastCategoryId);
            }
        }
        return category;
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM categories WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
