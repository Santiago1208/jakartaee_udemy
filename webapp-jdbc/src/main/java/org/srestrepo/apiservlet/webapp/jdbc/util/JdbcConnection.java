package org.srestrepo.apiservlet.webapp.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

    private static String url = "jdbc:postgres://localhost:5432/java_course";
    private static String username = "SANTIAGO";
    private static String password = "password";

    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, username, password);
    }
}
