package org.srestrepo.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jakarta_course";
        String username = "SANTIAGO";
        String password = "password";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
