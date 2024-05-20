package org.srestrepo.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/jakarta_course";
    private static final String USERNAME = "SANTIAGO";
    private static final String PASSWORD = "password";
    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        }
        return connection;
    }
}
