package org.srestrepo.java.jdbc.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/jakarta_course";
    private static final String USERNAME = "SANTIAGO";
    private static final String PASSWORD = "password";
    private static BasicDataSource pool;

    private DatabaseConnection() {}

    public static BasicDataSource getInstance() throws SQLException {
        if (pool == null) {
            pool = new BasicDataSource();
            // Basic configuration
            pool.setUrl(URL);
            pool.setUsername(USERNAME);
            pool.setPassword(PASSWORD);
            // Initial capacity
            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(8);
            pool.setMaxTotal(8);
        }
        return pool;
    }

    /**
     * Gets one connection from the already configured pool.
     * @return the connection object DAOs can use to do CRUD operations
     * @throws SQLException if the pool could not get a connection because they all are busy
     * or the pool is misconfigured.
     */
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
