package org.srestrepo.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jakarta_course";
        String username = "SANTIAGO2";
        String password = "password";

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM PRODUCTS");

            while (result.next()) {
                System.out.println(result.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                result.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
